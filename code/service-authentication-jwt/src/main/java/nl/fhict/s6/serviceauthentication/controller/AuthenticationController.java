package nl.fhict.s6.serviceauthentication.controller;

import io.jsonwebtoken.impl.DefaultClaims;
import nl.fhict.s6.serviceauthentication.datamodels.*;
import nl.fhict.s6.serviceauthentication.dto.*;
import nl.fhict.s6.serviceauthentication.event.UserCreatedEvent;
import nl.fhict.s6.serviceauthentication.exception.TokenRefreshException;
import nl.fhict.s6.serviceauthentication.security.jwt.JwtUtils;
import nl.fhict.s6.serviceauthentication.security.services.UserDetailsImpl;
import nl.fhict.s6.serviceauthentication.security.services.UserDetailsServiceImpl;
import nl.fhict.s6.serviceauthentication.service.RecaptchaService;
import nl.fhict.s6.serviceauthentication.service.RefreshTokenService;
import nl.fhict.s6.serviceauthentication.service.RoleService;
import nl.fhict.s6.serviceauthentication.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AmqpTemplate rabbitTemplate;
    private final UserDetailsServiceImpl userDetailsService;
    @Value("${gamehighlights.rabbitmq.exchange}")
    private String exchange;
    @Value("${gamehighlights.rabbitmq.routingkey}")
    private String routingkey;
    RefreshTokenService refreshTokenService;
    private RecaptchaService recaptchaService = new RecaptchaService(new RestTemplate());

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils, AmqpTemplate rabbitTemplate, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.rabbitTemplate = rabbitTemplate;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenByAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshTokenDao refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @RequestMapping("/register")
    public ResponseEntity register(@Valid @RequestBody SignupRequest signUpRequest)
    {
        recaptchaService.validateRecaptchaClientToken(signUpRequest.getToken());
        if (userService.checkUserExistByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.checkUserExistByUsername(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserDao userDao = new UserDao(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        userDao.setRoles(stringRolesToRoles(strRoles));
        UserDao saved = userService.save(userDao);
        rabbitTemplate.convertAndSend(exchange,routingkey,new UserCreatedEvent(saved.getId(),saved.getUsername()));
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    private Set<RoleDao> stringRolesToRoles(Set<String> stringRoles)
    {
        Set<RoleDao> roles = new HashSet<>();
        Set<RoleType> roleTypes = rolesToRoleTypes(stringRoles);
        for (RoleType roleType : roleTypes) {
            RoleDao userRole = roleService.findByType(roleType)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        return roles;
    }
    private Set<RoleType> rolesToRoleTypes(Set<String> stringRoles)
    {
        Set<RoleType> roleTypes = new HashSet<>();
        if(stringRoles == null || stringRoles.size() == 0)
        {
            roleTypes.add(RoleType.USER);
            return roleTypes;
        }
        try {
            stringRoles.forEach(role -> {
                RoleType.valueOf(role.toUpperCase());
            });
        }
        catch (Exception exception)
        {
           throw new RuntimeException("Error : role not found");
        }
        return roleTypes;
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenDao::getUser)
                .map(user -> {
                    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(user.getUsername());
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}

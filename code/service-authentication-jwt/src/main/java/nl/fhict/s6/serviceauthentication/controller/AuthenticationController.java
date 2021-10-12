package nl.fhict.s6.serviceauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fhict.s6.serviceauthentication.basemessaging.EncapsulatingMessageGenerator;
import nl.fhict.s6.serviceauthentication.datamodels.*;
import nl.fhict.s6.serviceauthentication.dto.JwtResponse;
import nl.fhict.s6.serviceauthentication.dto.LoginRequest;
import nl.fhict.s6.serviceauthentication.dto.MessageResponse;
import nl.fhict.s6.serviceauthentication.dto.SignupRequest;
import nl.fhict.s6.serviceauthentication.event.UserCreatedEvent;
import nl.fhict.s6.serviceauthentication.security.jwt.JwtUtils;
import nl.fhict.s6.serviceauthentication.security.services.UserDetailsImpl;
import nl.fhict.s6.serviceauthentication.recaptcha.RecaptchaService;
import nl.fhict.s6.serviceauthentication.service.RoleService;
import nl.fhict.s6.serviceauthentication.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Value("${gamehighlights.rabbitmq.exchange}")
    private String exchange;
    @Value("${gamehighlights.rabbitmq.routingkey}")
    private String routingkey;
    @Autowired
    private RecaptchaService recaptchaService;
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils,AmqpTemplate rabbitTemplate) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.rabbitTemplate = rabbitTemplate;
    }
    @RequestMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @RequestMapping("/register")
    public ResponseEntity register(@Valid @RequestBody SignupRequest signUpRequest) throws JsonProcessingException {
        boolean humanUser = recaptchaService.validateRecaptchaClientToken(signUpRequest.getToken());
        if(!humanUser)
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Not a valid user token"));
        }
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
        rabbitTemplate.convertAndSend(exchange,routingkey,new EncapsulatingMessageGenerator().generateMessageString(new UserCreatedEvent(saved.getId(),saved.getUsername())));
        System.out.println("user registerd");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    //later verbeteren
    private Set<RoleDao> stringRolesToRoles(Set<String> stringRoles)
    {
        Set<RoleDao> roles = new HashSet<>();
        if (stringRoles == null || stringRoles.size() < 1) {
            RoleDao userRole = roleService.findByType(RoleType.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            stringRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleDao adminRole = roleService.findByType(RoleType.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:new RuntimeException("Error: Role is not found.");
                }
            });
        }
        return roles;
    }
}

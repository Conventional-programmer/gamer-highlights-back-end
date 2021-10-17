package nl.fhict.s6.serviceauthentication.security.jwt;

import io.jsonwebtoken.*;
import io.micrometer.core.instrument.util.StringUtils;
import nl.fhict.s6.serviceauthentication.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${game-highlights.app.jwtSecret}")
    private String jwtSecret;

    @Value("${game-highlights.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public <T> T extractClaim(String token, String claim, Class<T> type) {
        Claims claims = extractAllClaims(token);
        for(Map.Entry<String,Object> claimToPrint: claims.entrySet())
        {
            System.out.println(claimToPrint.getKey());
        }
        return claims.get(claim, type);
    }

    private Claims extractAllClaims(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }


    public String generateJwtTokenByAuthentication(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("user princepal 1: " +userPrincipal.getId());
        return generateJwtTokenByUserDetails(userPrincipal);
    }
    public String generateJwtTokenByUserDetails(UserDetailsImpl userPrincipal)
    {
        Map<String, Object> claims = generateClaims(userPrincipal);
        String subject = Long.toString(userPrincipal.getId());
        System.out.println("user princepal 2: " + subject);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    private Map<String, Object> generateClaims(UserDetailsImpl userPrincipal)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userPrincipal.getEmail());
        claims.put("authorities", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return claims;
    }

    public String getSubjectFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}

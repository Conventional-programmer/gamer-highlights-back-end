package nl.fhict.s6.gateway.helper;

import nl.fhict.s6.gateway.dto.response.VerifyTokenResponse;
import nl.fhict.s6.gateway.token.ExternallyAuthenticatedAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationHelper {
    public void setSecurityContextByVerifyTokenResponse(VerifyTokenResponse verifyTokenResponse)
    {
        List<GrantedAuthority> authorities = verifyTokenResponse.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        SecurityContextHolder.getContext().setAuthentication(new ExternallyAuthenticatedAuthenticationToken(verifyTokenResponse.getId(), verifyTokenResponse.getEmail(), authorities));
    }
}

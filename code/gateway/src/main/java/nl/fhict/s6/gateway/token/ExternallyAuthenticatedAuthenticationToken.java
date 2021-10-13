package nl.fhict.s6.gateway.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ExternallyAuthenticatedAuthenticationToken extends AbstractAuthenticationToken {
    private final String id;
    private final Object principal;

    public ExternallyAuthenticatedAuthenticationToken(String id, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.principal = principal;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public String getId() {
        return id;
    }
}


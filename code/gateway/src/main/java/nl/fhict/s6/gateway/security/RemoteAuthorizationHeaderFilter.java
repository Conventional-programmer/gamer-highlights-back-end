package nl.fhict.s6.gateway.security;

import nl.fhict.s6.gateway.dto.request.VerifyJwtRequest;
import nl.fhict.s6.gateway.dto.response.VerifyTokenResponse;
import nl.fhict.s6.gateway.helper.AuthorizationHelper;
import nl.fhict.s6.gateway.service.AuthorizationService;
import nl.fhict.s6.gateway.token.ExternallyAuthenticatedAuthenticationToken;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RemoteAuthorizationHeaderFilter implements GlobalFilter, Ordered {

    private AuthorizationService authorizationService;

    public RemoteAuthorizationHeaderFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.length() > 0) {
            VerifyTokenResponse verifyTokenResponse = authorizationService.getVerifyTokenResponseByToken(authorizationHeader);
            if (verifyTokenResponse != null && verifyTokenResponse.isValid()) {
                new AuthorizationHelper().setSecurityContextByVerifyTokenResponse(verifyTokenResponse);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

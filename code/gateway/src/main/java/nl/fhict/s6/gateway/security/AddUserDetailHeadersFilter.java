package nl.fhict.s6.gateway.security;

import nl.fhict.s6.gateway.token.ExternallyAuthenticatedAuthenticationToken;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddUserDetailHeadersFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpRequest requestContext = exchange.getRequest();
            SecurityContext securityContext = SecurityContextHolder.getContext();
            requestContext.mutate().headers(h -> h.add("user_id", Long.toString(-1L)));
            //        requestContext.getHeaders().set("email", null);
            //        requestContext.getHeaders().add("roles", null);
            System.out.println(securityContext.getAuthentication() instanceof ExternallyAuthenticatedAuthenticationToken);
            if (securityContext.getAuthentication() instanceof ExternallyAuthenticatedAuthenticationToken) {
                ExternallyAuthenticatedAuthenticationToken authentication = (ExternallyAuthenticatedAuthenticationToken) securityContext.getAuthentication();
                List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                requestContext.mutate().headers(h -> h.set("user_id", authentication.getId()));
                //            requestContext.getHeaders().add("user_id", authentication.getId());
                //            requestContext.getHeaders().add("email", (String) authentication.getPrincipal());
                //            requestContext.getHeaders().add("roles",  Base64.getEncoder().encodeToString(listToByteArray(authorities)));
            }
            return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}

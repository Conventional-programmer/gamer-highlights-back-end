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
            //        requestContext.getHeaders().set("Subject", null);
            //        requestContext.getHeaders().add("Roles", null);
            System.out.println(securityContext.getAuthentication() instanceof ExternallyAuthenticatedAuthenticationToken);
            if (securityContext.getAuthentication() instanceof ExternallyAuthenticatedAuthenticationToken) {
                ExternallyAuthenticatedAuthenticationToken authentication = (ExternallyAuthenticatedAuthenticationToken) securityContext.getAuthentication();
                List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                System.out.println("User-id: " + authentication.getId());
                requestContext.mutate().headers(h -> h.set("User-Id", authentication.getId()));
                //            requestContext.getHeaders().add("Subject", (String) authentication.getPrincipal());
                //            requestContext.getHeaders().add("Roles",  Base64.getEncoder().encodeToString(listToByteArray(authorities)));
            }
            else {
                requestContext.mutate().headers(h -> h.set("User-Id", Long.toString(-1L)));
            }
            System.out.println("Header end result: "+requestContext.getHeaders().getFirst("User-Id"));
            return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}

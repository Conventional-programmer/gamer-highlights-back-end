package nl.fhict.s6.gateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// This should be replaced by making web security session less however I have not yet found a way to make this work with webflux
@Component
public class AuthResetFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        SecurityContextHolder.clearContext();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

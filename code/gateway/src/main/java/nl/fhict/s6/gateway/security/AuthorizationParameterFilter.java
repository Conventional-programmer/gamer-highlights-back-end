package nl.fhict.s6.gateway.security;

import nl.fhict.s6.gateway.dto.response.VerifyTokenResponse;
import nl.fhict.s6.gateway.helper.AuthorizationHelper;
import nl.fhict.s6.gateway.service.AuthorizationService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationParameterFilter implements GatewayFilterFactory<AuthorizationParameterFilter.Config>, Ordered {
    private AuthorizationService authorizationService;

    public AuthorizationParameterFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authorizationHeader = exchange.getRequest().getQueryParams().getFirst("Authorization");
            System.out.println("auth header " + authorizationHeader);
            if (authorizationHeader != null && authorizationHeader.length() > 0) {
                VerifyTokenResponse verifyTokenResponse = authorizationService.getVerifyTokenResponseByToken(authorizationHeader);
                if (verifyTokenResponse != null && verifyTokenResponse.isValid()) {
                    System.out.println("set security context");
                    new AuthorizationHelper().setSecurityContextByVerifyTokenResponse(verifyTokenResponse);
                }
            }
            return chain.filter(exchange);
        };
    }
    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config("AuthorizationParameterFilter");
    }

    public static class Config {

        public Config(String name){
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}

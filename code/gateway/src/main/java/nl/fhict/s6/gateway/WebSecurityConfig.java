package nl.fhict.s6.gateway;

import nl.fhict.s6.gateway.security.AuthEntryPointJwt;
import nl.fhict.s6.gateway.security.RemoteAuthorizationHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeExchange()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/user/**").permitAll()
                .pathMatchers("/post/**").permitAll()
                .pathMatchers("/comment/**").permitAll()
                .pathMatchers("/image/**").permitAll()
                .pathMatchers("/like/**").permitAll()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange().authenticated();
        //http.addFilterBefore(authenticationJwtTokenFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        return http.build();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/")//
                .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }
}

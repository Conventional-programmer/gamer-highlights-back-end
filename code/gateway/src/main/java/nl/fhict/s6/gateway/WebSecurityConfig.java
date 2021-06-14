package nl.fhict.s6.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    nl.fhict.s6.gateway.AuthenticationFilter authenticationFilter;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
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
        return http.build();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/")//
                .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }
    @Bean
    public RemoteAuthorizationHeaderFilter authenticationJwtTokenFilter() {
        return new RemoteAuthorizationHeaderFilter();
    }
}

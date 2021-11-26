package nl.fhict.s6.libraryrest.authentication.http;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoadUserFromHeaderFilter extends OncePerRequestFilter {
    public LoadUserFromHeaderFilter() {
    }

    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        PermissionHttpHeader permissionHttpHeader = new PermissionHttpHeader(httpServletRequest);
        filterChain.doFilter(permissionHttpHeader, httpServletResponse);
    }
}

package kz.warstation.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;

@Component
public class HostRoutingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String host = request.getHeader("Host");

        // Check for google.com and forward to localhost/google
        if ("google.com".equalsIgnoreCase(host)) {
            String redirectUrl = "http://localhost/" + request.getRequestURI();
            response.sendRedirect(redirectUrl); // Forward to localhost/google
        } else {
            filterChain.doFilter(request, response); // Allow normal controller processing
        }
    }
}
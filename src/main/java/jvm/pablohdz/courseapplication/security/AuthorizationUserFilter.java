package jvm.pablohdz.courseapplication.security;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for authorization users
 */
public class AuthorizationUserFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().equals("/api/login") ||
                request.getServletPath().equals("/api/token/refresh")) {
            filterChain.doFilter(request, response);

        } else {

        }
    }
}

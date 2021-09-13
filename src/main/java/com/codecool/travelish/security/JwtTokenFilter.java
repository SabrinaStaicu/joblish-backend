package com.codecool.travelish.security;

import com.codecool.travelish.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Our custom filter that validated the JWT tokens.
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenService jwtTokenService;

    @Autowired
    public JwtTokenFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    // this is called for every request that comes in (unless its filtered out before in the chain)
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenService.getTokenFromRequest((HttpServletRequest) req);
        if (token != null && jwtTokenService.validateToken(token)) {
            Authentication auth = jwtTokenService.parseUserFromTokenInfo(token);
            // Marks the user as authenticated.
            // If this code does not run, the request will fail for routes that are configured to need authentication
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        // process the next filter.
        filterChain.doFilter(req, res);
    }
}

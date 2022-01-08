package com.ResumeBuilder.ResumeBuilder.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    JwtTokeProvider tokeProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokeProvider jwtTokeProvider) {
        super(authenticationManager);
        tokeProvider = jwtTokeProvider;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = tokeProvider.getAuthentication(request);

        if(authentication!= null && tokeProvider.validateToken(request)){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}

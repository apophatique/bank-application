package com.sbrf.reboot.bankapplication.service.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public abstract class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
    public JwtAuthFilter(RequestMatcher matcher) {
        super(matcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token;
        try {
            token = takeToken(request);
        } catch (Exception e) {
            return anonymousToken();
        }
        return new JwtToken(token);
    }

    protected abstract String takeToken(HttpServletRequest request) throws AuthenticationException;

    private Authentication anonymousToken() {
        return new AnonymousAuthenticationToken(
                "ANONYMOUS",
                "ANONYMOUS",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);

    }
}
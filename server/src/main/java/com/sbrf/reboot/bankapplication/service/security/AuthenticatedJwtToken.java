package com.sbrf.reboot.bankapplication.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticatedJwtToken extends AbstractAuthenticationToken {
    private final String subject;

    public AuthenticatedJwtToken(final String subject, final Object details) {
        super(null);
        this.subject = subject; // username
        setDetails(details);    // id
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }
}

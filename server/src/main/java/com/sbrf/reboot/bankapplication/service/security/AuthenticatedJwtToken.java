package com.sbrf.reboot.bankapplication.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticatedJwtToken extends AbstractAuthenticationToken {
    private final String subject;

    public AuthenticatedJwtToken(final String subject) {
        super(null);
        this.subject = subject;
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

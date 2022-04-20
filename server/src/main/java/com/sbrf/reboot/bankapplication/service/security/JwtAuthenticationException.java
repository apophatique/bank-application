package com.sbrf.reboot.bankapplication.service.security;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException() {

    }

    public JwtAuthenticationException(final String message, final Exception e) {
        super(message);
    }
}

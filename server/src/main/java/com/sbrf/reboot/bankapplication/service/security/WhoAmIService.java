package com.sbrf.reboot.bankapplication.service.security;

import com.sbrf.reboot.bankapplication.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WhoAmIService {
    public User whoami() {
        return new User(SecurityContextHolder.getContext().getAuthentication());
    }
}

package com.sbrf.reboot.bankapplication.service.security;

import com.sbrf.reboot.bankapplication.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface IJwtService {
    Authentication parseToken(String token);
    String createToken(User user);
}

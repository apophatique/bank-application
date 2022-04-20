package com.sbrf.reboot.bankapplication.entities;

import com.sbrf.reboot.bankapplication.service.security.AuthenticatedJwtToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Slf4j
public class User {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "username", length = 256, nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(name = "password", length = 256, nullable = false)
    @Getter
    @Setter
    private String password;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final Authentication authentication) {
        if (authentication instanceof AuthenticatedJwtToken) {
            if (authentication.getDetails() instanceof Integer) {
                id = (Integer) authentication.getDetails();
            }
            if (authentication.getPrincipal() instanceof String) {
                username = (String) authentication.getPrincipal();
            }
        }
        password = null;
    }
}

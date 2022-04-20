package com.sbrf.reboot.bankapplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_id_seq")
    @Getter
    @Setter
    private Integer id;

    @Column(name="username", length=256, nullable=false)
    @Getter
    @Setter
    private String username;

    @Column(name="password", length=256, nullable=false)
    @Getter
    @Setter
    private String password;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}

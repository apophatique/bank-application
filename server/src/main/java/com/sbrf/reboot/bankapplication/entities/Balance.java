package com.sbrf.reboot.bankapplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="balance")
@NoArgsConstructor
public class Balance {
    @Id
    @SequenceGenerator(name="balance_id_seq", sequenceName="balance_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="balance_id_seq")
    @Getter
    @Setter
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;


    @Column(name = "amount", nullable = false)
    @Getter
    @Setter
    private Long amount = 0L;
}

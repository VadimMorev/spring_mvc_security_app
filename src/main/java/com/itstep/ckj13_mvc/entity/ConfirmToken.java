package com.itstep.ckj13_mvc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ConfirmToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;
    private LocalDateTime createdAt;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    public ConfirmToken(User user) {
        this.value = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }
}

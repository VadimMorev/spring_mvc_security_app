package com.itstep.ckj13_mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@EnableJpaAuditing
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String message;
    @LastModifiedDate
    @Column(name = "date", nullable = false)
    private LocalDateTime dateOfCreation;
}

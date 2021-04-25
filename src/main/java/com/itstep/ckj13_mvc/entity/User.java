package com.itstep.ckj13_mvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private String firstname;
    private String lastname;
    private int age;
    private boolean enabled;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Note> notes;

    public void addNote(Note note) {
        note.setUser(this);
        notes.add(note);
    }

}

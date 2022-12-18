package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "usr")
@EqualsAndHashCode(of = "cookie")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String cookie;

    public User(String cookie) {
        this.cookie = cookie;
    }
}

package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Setter
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


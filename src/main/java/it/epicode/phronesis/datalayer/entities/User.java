package it.epicode.phronesis.datalayer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class User extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 125, nullable = false)
    private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    private String bio;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private final List<Roles> roles = new ArrayList<>();

    @Column(nullable = false)
    private boolean enabled;

    // Campo per gestire lo stato di ban
    @Column(nullable = false)
    private boolean banned;
}
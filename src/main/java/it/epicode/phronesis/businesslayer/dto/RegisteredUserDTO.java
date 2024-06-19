package it.epicode.phronesis.businesslayer.dto;

import it.epicode.phronesis.datalayer.entities.Roles;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RegisteredUserDTO {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String firstName;
    String lastName;
    String username;
    String email;
    String profilePicture;
    String bio;
    private final List<Roles> roles;
    boolean enabled;
    boolean banned;

    @Builder(setterPrefix = "with")
    public RegisteredUserDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName, String lastName, String username, String email, String profilePicture, String bio, List<Roles> roles, boolean enabled, boolean banned) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.roles = roles;
        this.enabled = enabled;
        this.banned = banned;
    }
}

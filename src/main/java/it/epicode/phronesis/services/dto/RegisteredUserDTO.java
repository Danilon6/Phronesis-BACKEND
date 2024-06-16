package it.epicode.phronesis.services.dto;

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

    @Builder(setterPrefix = "with")
    public RegisteredUserDTO(Long id, String firstName, String lastName, String username, String email, List<Roles> roles, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
    }
}

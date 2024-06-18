package it.epicode.phronesis.businesslayer.dto;

import it.epicode.phronesis.datalayer.entities.Roles;

import java.time.LocalDateTime;
import java.util.List;

public interface RegisteredUserPrj extends BaseProjection{
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getFirstName();
    String getLastName();
    String getUsername();
    String getEmail();
    String getProfilePicture();
    String getBio();
    List<Roles> getRoles();
    boolean getEnabled();
}

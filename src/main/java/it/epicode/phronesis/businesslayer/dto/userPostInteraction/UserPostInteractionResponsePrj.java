package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;

import java.time.LocalDateTime;

public interface UserPostInteractionResponsePrj {

    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    User getUser();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
    }
}

package it.epicode.phronesis.businesslayer.dto.userPostInteraction;


import it.epicode.phronesis.businesslayer.dto.BaseProjection;

import java.time.LocalDateTime;

public interface UserPostInteractionResponsePrj extends BaseProjection {

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

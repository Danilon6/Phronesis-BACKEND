package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.datalayer.entities.User;

import java.time.LocalDateTime;

public interface CommentResponsePrj extends BaseProjection {

    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    User getUser();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
    }

    String getContent();
}

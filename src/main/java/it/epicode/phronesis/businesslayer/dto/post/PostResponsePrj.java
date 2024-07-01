package it.epicode.phronesis.businesslayer.dto.post;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;

import java.time.LocalDateTime;
import java.util.List;

public interface PostResponsePrj extends BaseProjection {
    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    String getTitle();

    String getContent();

    User getUser();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
    }

    List<CommentResponsePrj> getComments();

    List<UserPostInteractionResponsePrj> getLikes();
}

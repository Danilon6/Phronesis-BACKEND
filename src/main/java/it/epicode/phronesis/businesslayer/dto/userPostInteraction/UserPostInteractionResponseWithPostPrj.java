package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;


import java.time.LocalDateTime;
import java.util.List;

public interface UserPostInteractionResponseWithPostPrj extends BaseProjection {

    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Post getPost();

    interface Post {
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
}

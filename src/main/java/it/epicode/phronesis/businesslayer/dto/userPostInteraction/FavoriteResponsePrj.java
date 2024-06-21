package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.datalayer.entities.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface FavoriteResponsePrj extends UserPostInteractionResponsePrj{

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

package it.epicode.phronesis.businesslayer.services.impl;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostToPostResponseDTOMapperImpl implements Mapper<Post, PostResponseDTO> {

    @Autowired
    Mapper<User, UserResponsePartialDTO> mapUserEntity2UserResponsePartialDTO;

    private List<CommentResponseDTO> comments;
    private List<UserPostInteractionResponseDTO> likes;


    @Builder(setterPrefix = "with")
    public PostToPostResponseDTOMapperImpl(List<CommentResponseDTO> comments, List<UserPostInteractionResponseDTO> likes) {
        this.comments = comments;
        this.likes = likes;
    }

    @Override
        public PostResponseDTO map(Post input) {
            return PostResponseDTO.builder()
                    .withId(input.getId())
                    .withCreatedAt(input.getCreatedAt())
                    .withUpdatedAt(input.getUpdatedAt())
                    .withTitle(input.getTitle())
                    .withContent(input.getContent())
                    .withImageUrl(input.getImageUrl())
                    .withUser(mapUserEntity2UserResponsePartialDTO.map(input.getUser()))
                    .withComments(comments)
                    .withLikes(likes)
                    .build();
        }
}

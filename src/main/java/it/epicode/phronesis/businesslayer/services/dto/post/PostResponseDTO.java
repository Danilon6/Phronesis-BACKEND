package it.epicode.phronesis.businesslayer.services.dto.post;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.services.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionResponseDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostResponseDTO extends BaseDTO {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String title;

    private String content;

    private String imageUrl;

    private UserResponsePartialDTO user;

    private List<CommentResponseDTO> comments;

    private List<UserPostInteractionResponseDTO> likes;
}

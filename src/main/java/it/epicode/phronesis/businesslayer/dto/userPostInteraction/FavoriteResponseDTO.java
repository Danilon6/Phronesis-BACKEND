package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResponseDTO extends UserPostInteractionResponseDTO {

    private PostResponseDTO post;

    @Builder(builderMethodName = "favoriteResponseBuilder", setterPrefix = "with")
    public FavoriteResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, UserResponsePartialDTO user, PostResponseDTO post) {
        super(id, createdAt, updatedAt, user);
        this.post = post;
    }
}

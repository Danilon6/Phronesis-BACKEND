package it.epicode.phronesis.businesslayer.dto.userPostInteraction;


import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import lombok.*;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO extends UserPostInteractionResponseDTO {

    private String content;

    @Builder(builderMethodName = "commentResponseBuilder", setterPrefix = "with")
    public CommentResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, UserResponsePartialDTO user, String content) {
        super(id, createdAt, updatedAt, user);
        this.content = content;
    }
}

package it.epicode.phronesis.businesslayer.services.dto.userPostInteraction;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO extends UserPostInteractionRequestDTO {

    private String content;
}

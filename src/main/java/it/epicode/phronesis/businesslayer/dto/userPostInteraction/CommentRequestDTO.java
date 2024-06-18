package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO extends UserPostInteractionRequestDTO {

    private String content;

    @Builder(builderMethodName = "commentRequestBuilder", setterPrefix = "with")
    public CommentRequestDTO(Long userId, Long postId, String content) {
        super(userId, postId);
        this.content = content;
    }
}

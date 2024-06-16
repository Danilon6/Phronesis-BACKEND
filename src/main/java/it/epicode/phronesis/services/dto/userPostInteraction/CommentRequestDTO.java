package it.epicode.phronesis.services.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CommentRequestDTO extends BaseDTO {

    private Long postId;

    private Long userId;

    private String content;
}

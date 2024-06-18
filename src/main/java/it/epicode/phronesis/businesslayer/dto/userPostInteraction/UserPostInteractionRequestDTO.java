package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserPostInteractionRequestDTO extends BaseDTO {
    private Long userId;
    private Long postId;
}

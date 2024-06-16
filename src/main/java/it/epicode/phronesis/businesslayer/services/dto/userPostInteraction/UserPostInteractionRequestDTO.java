package it.epicode.phronesis.businesslayer.services.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
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

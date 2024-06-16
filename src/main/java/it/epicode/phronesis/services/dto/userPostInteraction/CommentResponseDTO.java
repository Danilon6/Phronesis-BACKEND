package it.epicode.phronesis.services.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.datalayer.entities.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CommentResponseDTO extends BaseDTO {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private User user;

    private String content;
}

package it.epicode.phronesis.services.dto.post;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
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

    private Long userId;

    private List<Comment> comments;
}

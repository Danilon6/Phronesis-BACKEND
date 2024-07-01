package it.epicode.phronesis.businesslayer.dto.post;

import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostEditRequestDTO extends BaseDTO {
    private String title;

    private String content;
}

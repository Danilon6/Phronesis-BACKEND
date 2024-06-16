package it.epicode.phronesis.businesslayer.services.dto.post;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostRequestDTO extends BaseDTO {

    private String title;

    private String content;

    private String imageUrl;

    private Long userId;

}

package it.epicode.phronesis.businesslayer.dto.post;

import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostPartialResponseDTO extends BaseDTO {

    private Long id;
    private String title;

}

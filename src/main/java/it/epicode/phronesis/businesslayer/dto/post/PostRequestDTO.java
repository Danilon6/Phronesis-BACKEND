package it.epicode.phronesis.businesslayer.dto.post;

import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostRequestDTO extends BaseDTO {

    private String title;

    private String content;

    private MultipartFile imageFile;

    private Long userId;

}

package it.epicode.phronesis.businesslayer.services.dto.report;

import it.epicode.phronesis.businesslayer.services.dto.post.PostPartialResponseDTO;
import it.epicode.phronesis.datalayer.entities.Post;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostReportResponseDTO extends ReportResponseDTO{

    private PostPartialResponseDTO reportedPost;
}

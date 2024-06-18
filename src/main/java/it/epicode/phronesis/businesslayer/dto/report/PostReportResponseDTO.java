package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostReportResponseDTO extends ReportResponseDTO{

    private PostPartialResponseDTO reportedPost;
}

package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostReportResponseDTO extends ReportResponseDTO{

    private PostPartialResponseDTO reportedPost;

    @Builder(builderMethodName = "postReportResponseBuilder", setterPrefix = "with")
    public PostReportResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, UserResponsePartialDTO reportedBy, String reason, PostPartialResponseDTO reportedPost) {
        super(id, createdAt, updatedAt, reportedBy, reason);
        this.reportedPost = reportedPost;
    }
}

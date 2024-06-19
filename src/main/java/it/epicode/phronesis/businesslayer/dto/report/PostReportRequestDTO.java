package it.epicode.phronesis.businesslayer.dto.report;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostReportRequestDTO extends ReportRequestDTO {

    private Long reportedPostId;

    @Builder(builderMethodName = "postReportRequestBuilder", setterPrefix = "with")
    public PostReportRequestDTO(Long reportedById, String reason, Long reportedPostId) {
        super(reportedById, reason);
        this.reportedPostId = reportedPostId;
    }
}

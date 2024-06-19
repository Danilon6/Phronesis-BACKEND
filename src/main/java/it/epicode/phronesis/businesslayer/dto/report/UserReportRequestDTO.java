package it.epicode.phronesis.businesslayer.dto.report;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserReportRequestDTO extends ReportRequestDTO {

    private Long reportedUserId;

    @Builder(builderMethodName = "userReportRequestBuilder", setterPrefix = "with")
    public UserReportRequestDTO(Long reportedById, String reason, Long reportedUserId) {
        super(reportedById, reason);
        this.reportedUserId = reportedUserId;
    }
}

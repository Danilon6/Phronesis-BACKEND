package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserReportResponseDTO extends ReportResponseDTO{

    private UserResponsePartialDTO reportedUser;

    @Builder(builderMethodName = "userReportResponseBuilder", setterPrefix = "with")
    public UserReportResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, UserResponsePartialDTO reportedBy, String reason, UserResponsePartialDTO reportedUser) {
        super(id, createdAt, updatedAt, reportedBy, reason);
        this.reportedUser = reportedUser;
    }
}

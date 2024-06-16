package it.epicode.phronesis.businesslayer.services.dto.report;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserReportRequestDTO extends ReportRequestDTO {

    private Long reportedUserId;
}

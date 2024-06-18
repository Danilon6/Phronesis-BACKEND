package it.epicode.phronesis.businesslayer.dto.report;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostReportRequestDTO extends ReportRequestDTO {

    private Long reportedPostId;
}

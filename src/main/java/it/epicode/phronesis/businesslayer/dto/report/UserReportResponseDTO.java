package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserReportResponseDTO extends ReportResponseDTO{

    private UserResponsePartialDTO reportedUser;
}

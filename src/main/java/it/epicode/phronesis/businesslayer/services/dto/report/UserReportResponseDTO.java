package it.epicode.phronesis.businesslayer.services.dto.report;

import it.epicode.phronesis.datalayer.entities.User;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserReportResponseDTO extends ReportResponseDTO{

    private User reportedUser;
}

package it.epicode.phronesis.businesslayer.services.dto.report;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.services.dto.UserResponsePartialDTO;
import it.epicode.phronesis.datalayer.entities.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDTO extends BaseDTO {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserResponsePartialDTO reportedBy;

    private String reason;

}

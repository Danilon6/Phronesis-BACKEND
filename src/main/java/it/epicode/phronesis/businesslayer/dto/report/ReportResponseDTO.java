package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.BaseDTO;
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

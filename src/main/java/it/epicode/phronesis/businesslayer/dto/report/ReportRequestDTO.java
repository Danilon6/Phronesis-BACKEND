package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDTO extends BaseDTO {

    private Long reportedById;

    private String reason;
}

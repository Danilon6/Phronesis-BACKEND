package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.BaseEntity;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public class Report extends BaseEntity {

    @ManyToOne
    private User reportedBy;

    private String reason;
}

package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.BaseEntity;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Report extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User reportedBy;

    @Column(nullable = false, length = 200)
    private String reason;
}

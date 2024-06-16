package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "userReports")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
@DiscriminatorValue("U")
public class UserReport extends Report{

    @ManyToOne
    private User reportedUser;
}

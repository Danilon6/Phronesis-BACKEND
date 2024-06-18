package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@DiscriminatorValue("U")
@Builder(setterPrefix = "with")
public class UserReport extends Report{

    @ManyToOne
    private User reportedUser;
}

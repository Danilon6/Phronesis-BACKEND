package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("U")
public class UserReport extends Report{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User reportedUser;

    @Builder(setterPrefix = "with")
    public UserReport(User reportedBy, String reason, User reportedUser) {
        super(reportedBy, reason);
        this.reportedUser = reportedUser;
    }
}

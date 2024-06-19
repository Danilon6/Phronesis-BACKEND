package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("P")
public class PostReport extends Report{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post reportedPost;

    @Builder(setterPrefix = "with")
    public PostReport(User reportedBy, String reason, Post reportedPost) {
        super(reportedBy, reason);
        this.reportedPost = reportedPost;
    }
}

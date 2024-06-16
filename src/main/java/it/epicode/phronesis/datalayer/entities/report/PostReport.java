package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.Post;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "postReports")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
@DiscriminatorValue("P")
public class PostReport extends Report{

    @ManyToOne
    private Post reportedPost;
}

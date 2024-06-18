package it.epicode.phronesis.datalayer.entities.report;

import it.epicode.phronesis.datalayer.entities.Post;
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
@DiscriminatorValue("P")
@Builder(setterPrefix = "with")
public class PostReport extends Report{

    @ManyToOne
    private Post reportedPost;
}

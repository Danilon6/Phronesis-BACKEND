package it.epicode.phronesis.datalayer.entities.userPostInteraction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.phronesis.datalayer.entities.BaseEntity;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "interaction_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class UserPostInteraction extends BaseEntity {

    @ManyToOne
    private User user;
    @ManyToOne
    @JsonBackReference
    private Post post;
}

package it.epicode.phronesis.datalayer.entities.userPostInteraction;

import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@DiscriminatorValue("F")
public class Favorite extends UserPostInteraction {

    @Builder(setterPrefix = "with")
    public Favorite(User user, Post post) {
        super(user, post);
    }
}
package it.epicode.phronesis.datalayer.entities.userPostInteraction;

import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@DiscriminatorValue("L")
public class Like extends UserPostInteraction{

    @Builder(setterPrefix = "with")
    public Like(User user, Post post) {
        super(user, post);
    }

}

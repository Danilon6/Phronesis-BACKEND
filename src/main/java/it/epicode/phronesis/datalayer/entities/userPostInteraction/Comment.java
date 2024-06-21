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
@DiscriminatorValue("C")
public class Comment extends UserPostInteraction{
    private String content;

    @Builder(setterPrefix = "with")
    public Comment(User user, Post post, String content) {
        super(user, post);
        this.content = content;
    }

    public Comment() {
    }
}

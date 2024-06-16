package it.epicode.phronesis.datalayer.entities;

import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "posts")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Post extends BaseEntity{

    private String title;

    private String content;

    private String imageUrl;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments;

}

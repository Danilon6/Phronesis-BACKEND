package it.epicode.phronesis.datalayer.entities;

import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import jakarta.persistence.*;
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

    @Column(length = 100, nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments;

}

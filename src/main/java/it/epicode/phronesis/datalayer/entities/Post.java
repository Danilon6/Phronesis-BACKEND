package it.epicode.phronesis.datalayer.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Post extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    private User user;

    //di defualt le many sono lazy
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Like> likes;

}

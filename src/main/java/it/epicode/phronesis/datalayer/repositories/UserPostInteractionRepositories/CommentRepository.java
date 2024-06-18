package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;

import java.util.List;

public interface CommentRepository extends UserPostInteractionRepository<Comment>{

    List<Comment> findByPostId(Long postId);
}

package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepository extends UserPostInteractionRepository<Comment>{

    List<CommentResponsePrj> findByPostId(Long postId);

    Page<CommentResponsePrj> findAllBy(Pageable pageable);
}

package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends UserPostInteractionRepository<Like> {

    List<UserPostInteractionResponsePrj> findByUserId(Long id);

    List<UserPostInteractionResponsePrj> findByPostId(Long postId);

}

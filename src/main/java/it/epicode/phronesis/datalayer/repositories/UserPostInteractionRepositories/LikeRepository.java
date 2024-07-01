package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseWithPostPrj;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;

import java.util.List;

public interface LikeRepository extends UserPostInteractionRepository<Like> {

    List<UserPostInteractionResponseWithPostPrj> findByUserId(Long id);

    List<UserPostInteractionResponsePrj> findByPostId(Long postId);

}

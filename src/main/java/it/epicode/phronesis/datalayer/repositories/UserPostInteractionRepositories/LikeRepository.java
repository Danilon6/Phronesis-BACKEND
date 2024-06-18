package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;

import java.util.List;

public interface LikeRepository extends UserPostInteractionRepository<Like> {

    List<Like> findByPostId(Long postId);
}

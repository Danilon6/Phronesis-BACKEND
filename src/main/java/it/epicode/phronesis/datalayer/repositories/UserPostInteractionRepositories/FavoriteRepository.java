package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponsePrj;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FavoriteRepository extends UserPostInteractionRepository<Favorite>  {
    Page<FavoriteResponsePrj> findAllByUserId(Pageable pageable, Long id);
    void deleteByPost(Post post);
}

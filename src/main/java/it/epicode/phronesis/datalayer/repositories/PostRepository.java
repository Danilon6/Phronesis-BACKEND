package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponsePrj;
import it.epicode.phronesis.datalayer.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostRepository extends
        JpaRepository<Post, Long>,
        PagingAndSortingRepository<Post, Long> {

    Optional<Post> findByTitle(String title);
    Page<PostResponsePrj> findAllBy(Pageable pageable);
    Page<PostResponsePrj> findAllByUserId(Pageable pageable, Long id);
}

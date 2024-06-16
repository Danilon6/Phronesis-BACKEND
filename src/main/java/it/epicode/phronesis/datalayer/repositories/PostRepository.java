package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.datalayer.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends
        JpaRepository<Post, Long>,
        PagingAndSortingRepository<Post, Long> {
}

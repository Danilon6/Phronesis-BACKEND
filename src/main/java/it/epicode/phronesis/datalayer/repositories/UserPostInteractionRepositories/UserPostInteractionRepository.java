package it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories;

import it.epicode.phronesis.datalayer.entities.userPostInteraction.UserPostInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPostInteractionRepository<T extends UserPostInteraction> extends
        JpaRepository<T, Long>,
        PagingAndSortingRepository<T, Long> {
}

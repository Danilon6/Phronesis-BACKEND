package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.businesslayer.dto.AdvertResponsePrj;
import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;
import it.epicode.phronesis.datalayer.entities.Advert;
import it.epicode.phronesis.datalayer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AdvertRepository extends
        JpaRepository<Advert, Long>,
        PagingAndSortingRepository<Advert, Long> {

    Page<AdvertResponsePrj> findAllBy(Pageable pageable);
    Optional<Advert> findOneByTitle(String title);
}

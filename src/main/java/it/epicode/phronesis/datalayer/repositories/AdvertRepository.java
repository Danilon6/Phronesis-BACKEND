package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.datalayer.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdvertRepository extends
        JpaRepository<Advert, Long>,
        PagingAndSortingRepository<Advert, Long> {
}

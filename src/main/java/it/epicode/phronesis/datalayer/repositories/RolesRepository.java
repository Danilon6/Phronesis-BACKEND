package it.epicode.phronesis.datalayer.repositories;


import it.epicode.phronesis.datalayer.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RolesRepository extends
        JpaRepository<Roles, Long>,
        PagingAndSortingRepository<Roles, Long> {

    Optional<Roles> findOneByRoleType(String roleType);
}

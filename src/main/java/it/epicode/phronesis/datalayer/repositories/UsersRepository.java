package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.businesslayer.dto.RegisteredUserPrj;
import it.epicode.phronesis.datalayer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findOneByUsernameAndPassword(String username, String password);
	Optional<User> findOneByUsername(String username);
	List<User> findByRoles_RoleType(String role);
	Page<RegisteredUserPrj> findAllBy(Pageable pageable);
	Page<RegisteredUserPrj> findAllByBanned(boolean banned, Pageable pageable);

}

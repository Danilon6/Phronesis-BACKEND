package it.epicode.phronesis.datalayer.repositories;

import it.epicode.phronesis.businesslayer.dto.FollowResponsePrj;
import it.epicode.phronesis.datalayer.entities.Follow;
import it.epicode.phronesis.datalayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<FollowResponsePrj> findByFollower(User follower);
    List<FollowResponsePrj> findByFollowing(User following);
    Optional<Follow> findByFollowerAndFollowing(User follower, User following);
    List<Long> findIdByFollower(User follower);
    List<Long> findIdByFollowing(User following);
    List<Follow> findAllByFollower(User follower);
    List<Follow> findAllByFollowing(User following);

}
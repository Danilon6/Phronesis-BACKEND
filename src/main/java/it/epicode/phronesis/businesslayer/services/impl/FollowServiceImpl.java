package it.epicode.phronesis.businesslayer.services.impl;

import it.epicode.phronesis.businesslayer.dto.FollowResponseDTO;
import it.epicode.phronesis.businesslayer.dto.FollowResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.FollowService;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.datalayer.entities.Follow;
import it.epicode.phronesis.datalayer.repositories.FollowRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.exceptions.user.follow.AlreadyFollowingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.user.follow.AlreadyNotFollowingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    Mapper<Follow, FollowResponseDTO> mapFollowEntityToFollowResponseDTO;

    @Override
    public List<FollowResponsePrj> getFollowers(Long followingId) {
        var followingUser = usersRepository.findById(followingId).orElseThrow(()-> new NotFoundException(followingId));
        //Ottengo tutti i follower di un utente cercando le istanze di follow dove il following(l'utente seguito) è lo user che ho trovato
        return followRepository.findByFollowing(followingUser);
    }

    @Override
    public List<FollowResponsePrj> getFollowing(Long followerId) {
        var followerUser = usersRepository.findById(followerId).orElseThrow(()-> new NotFoundException(followerId));
        //Ottengo tutti gli user che l'utente trovato segue cercando le istanze di follow dove il follower(l'utente che segue) è lo user che ho trovato
        return followRepository.findByFollower(followerUser);
    }

    @Transactional
    @Override
    public FollowResponseDTO followUser(Long followerId, Long followingId) {
        var follower = usersRepository.findById(followerId).orElseThrow(()-> new NotFoundException(followerId));
        var following = usersRepository.findById(followingId).orElseThrow(()-> new NotFoundException(followingId));
        var follow = followRepository.findByFollowerAndFollowing(follower, following);

        if (follow.isPresent()) {
           throw  new AlreadyFollowingException(followerId, followingId);
        }

        return mapFollowEntityToFollowResponseDTO.map(followRepository.save(Follow.builder()
                .withFollower(follower)
                .withFollowing(following)
                .build()));
    }

    @Override
    @Transactional
    public FollowResponseDTO unfollowUser(Long followerId, Long followingId) {
        var follower = usersRepository.findById(followerId).orElseThrow(()-> new NotFoundException(followerId));
        var following = usersRepository.findById(followingId).orElseThrow(()-> new NotFoundException(followingId));
        var follow = followRepository.findByFollowerAndFollowing(follower, following);

        if (follow.isEmpty()) {
            throw  new AlreadyNotFollowingException(followerId, followingId);
        }

        followRepository.delete(follow.get());

        return mapFollowEntityToFollowResponseDTO.map(follow.get());
    }

    @Override
    public boolean isFollowing(Long followerId, Long followingId) {
        var follower = usersRepository.findById(followerId).orElseThrow(()-> new NotFoundException(followerId));
        var following = usersRepository.findById(followingId).orElseThrow(()-> new NotFoundException(followingId));
        return followRepository.findByFollowerAndFollowing(follower, following).isPresent();
    }


}

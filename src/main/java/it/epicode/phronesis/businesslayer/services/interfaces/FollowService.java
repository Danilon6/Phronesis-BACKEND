package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.FollowResponseDTO;
import it.epicode.phronesis.businesslayer.dto.FollowResponsePrj;

import java.util.List;

public interface FollowService {

    List<FollowResponsePrj> getFollowers(Long followingId);
    List<FollowResponsePrj> getFollowing(Long followerId);
    FollowResponseDTO followUser(Long followerId, Long followingId);
    FollowResponseDTO unfollowUser(Long followerId, Long followingId);
    boolean isFollowing(Long followerId, Long followingId);
}

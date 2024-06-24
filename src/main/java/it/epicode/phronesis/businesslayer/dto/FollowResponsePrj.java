package it.epicode.phronesis.businesslayer.dto;

public interface FollowResponsePrj extends BaseProjection{

   User getFollower();
   User getFollowing();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
    }
}

package it.epicode.phronesis.presentationlayer.api.exceptions.user.follow;

public class AlreadyNotFollowingException extends FollowException {
    public AlreadyNotFollowingException(Long followerId, Long followingId) {
            super("User with ID " + followerId + " is already not following user with ID " + followingId);
        }
}


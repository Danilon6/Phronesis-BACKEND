package it.epicode.phronesis.presentationlayer.api.exceptions.user.follow;

public class AlreadyFollowingException extends FollowException {
    public AlreadyFollowingException(Long followerId, Long followingId) {
        super("User with ID " + followerId + " is already following user with ID " + followingId);
    }
}

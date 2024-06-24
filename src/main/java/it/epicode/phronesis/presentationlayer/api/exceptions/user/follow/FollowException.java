package it.epicode.phronesis.presentationlayer.api.exceptions.user.follow;

import java.io.Serial;

public class FollowException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public FollowException(String message) {
        super(message);
    }
}

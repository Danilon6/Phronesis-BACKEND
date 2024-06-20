package it.epicode.phronesis.presentationlayer.api.exceptions.user;

import java.io.Serial;

public class UserAlreadyUnbannedException extends UserBanException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyUnbannedException(String username, String message) {
        super(username, message);
    }

    public UserAlreadyUnbannedException(String username) {
        this(username, "user is already unbanned" );
    }
}

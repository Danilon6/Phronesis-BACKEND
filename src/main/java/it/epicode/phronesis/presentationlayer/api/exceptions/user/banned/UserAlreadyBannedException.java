package it.epicode.phronesis.presentationlayer.api.exceptions.user.banned;

import java.io.Serial;

public class UserAlreadyBannedException extends UserBanException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyBannedException(String username, String message) {
        super(username, message);
    }

    public UserAlreadyBannedException(String username) {
        this(username, "user is already banned" );
    }
}

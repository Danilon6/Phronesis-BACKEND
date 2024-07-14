package it.epicode.phronesis.presentationlayer.api.exceptions.user.banned;

import java.io.Serial;

public class UserBannedException extends UserBanException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserBannedException(String username, String message) {
        super(username, message);
    }

    public UserBannedException(String username) {
        this(username, "user is banned" );
    }
}

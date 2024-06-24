package it.epicode.phronesis.presentationlayer.api.exceptions.user.banned;

import java.io.Serial;

public class UserBanException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public final String username;

    public UserBanException(String username) {
        this(username, "Exception ban user");
    }

    public UserBanException(String username, String message) {
        super(message);
        this.username = username;
    }
}

package it.epicode.phronesis.presentationlayer.api.exceptions.user.enabled;

import java.io.Serial;

public class UserIsAlreadyEnabledException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public final String username;

    public UserIsAlreadyEnabledException(String username, String message) {
        super(message);
        this.username = username;
    }

    public UserIsAlreadyEnabledException(String username) {
        this(username, "User is already enabled");
    }
}

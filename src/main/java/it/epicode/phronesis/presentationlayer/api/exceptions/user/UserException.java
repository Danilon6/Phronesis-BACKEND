package it.epicode.phronesis.presentationlayer.api.exceptions.user;

import java.io.Serial;

public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public final String username;
    public final String password;

    public UserException(String username, String password, String message) {
        super(message);
        this.username = username;
        this.password = password;
    }
}

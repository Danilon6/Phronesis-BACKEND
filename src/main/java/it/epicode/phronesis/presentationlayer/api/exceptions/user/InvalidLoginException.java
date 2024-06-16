package it.epicode.phronesis.presentationlayer.api.exceptions.user;

import java.io.Serial;

public class InvalidLoginException extends UserException{

    @Serial
    private static final long serialVersionUID = 1L;


    public InvalidLoginException(String username, String password, String message) {
        super(username, password, message);
    }

    public InvalidLoginException(String username, String password) {
        this(username, password, "Invalid credentials" );
    }
}

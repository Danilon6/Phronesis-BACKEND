package it.epicode.phronesis.presentationlayer.api.exceptions.user.enabled;

import java.io.Serial;

public class UserNotEnabledException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public final String username;
    public final String password;

    // Costruttore che accetta username, password e un messaggio personalizzato
    public UserNotEnabledException(String username, String password, String message) {
        super(message);
        this.username = username;
        this.password = password;
    }

    // Costruttore che utilizza un messaggio predefinito
    public UserNotEnabledException(String username, String password) {
        this(username, password, "User is not enabled");
    }
}

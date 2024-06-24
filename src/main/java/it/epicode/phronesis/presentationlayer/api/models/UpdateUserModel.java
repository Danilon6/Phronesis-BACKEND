package it.epicode.phronesis.presentationlayer.api.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserModel (

        @NotBlank(message = "Il tuo nome non può essere vuoto")
        String firstName,

        @NotBlank(message = "Il tuo cognome non può essere vuoto")
        String lastName,

        @Email(message = "Inserisci una email valida")
        String email,

        @NotBlank(message = "Inserisci una breve descrizione di te")
        String bio
) {
}

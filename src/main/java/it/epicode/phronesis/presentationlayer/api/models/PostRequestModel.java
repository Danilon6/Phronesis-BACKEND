package it.epicode.phronesis.presentationlayer.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PostRequestModel (
        @NotBlank(message = "il titolo non può essere vuoto")
        String title,
        @NotBlank(message = "Il tuo post deve avere un contenuto")
        @Size(min = 100, max = 500, message = "Il contenuto del tuo post deve essere tra 200 e 500 caratteri")
        String content,
        @Positive(message = "Lo userId dell'autore del post non può essere un numero negativo")
        Long userId
) {
}

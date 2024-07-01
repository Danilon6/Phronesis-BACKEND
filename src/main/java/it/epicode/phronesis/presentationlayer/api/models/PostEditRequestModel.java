package it.epicode.phronesis.presentationlayer.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostEditRequestModel (
        @NotBlank(message = "il titolo non può essere vuoto")
        String title,
        @NotBlank(message = "Il tuo post deve avere un contenuto")
        @Size(max = 500, message = "Il contenuto del tuo post è troppo lungo")
        String content
) {
}

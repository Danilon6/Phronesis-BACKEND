package it.epicode.phronesis.presentationlayer.api.models.userInteractionPost;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CommentRequestModel (
        @Positive(message = "Lo userId non può essere un numero negativo")
        Long userId,
        @Positive(message = "Il postId non può essere un numero negativo")
        Long postId,
        @NotBlank(message = "Il tuo commento non può essere vuoto")
        String content
) {
}

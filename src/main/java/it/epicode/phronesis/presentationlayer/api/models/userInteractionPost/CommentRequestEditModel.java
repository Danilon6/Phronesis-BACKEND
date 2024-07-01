package it.epicode.phronesis.presentationlayer.api.models.userInteractionPost;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestEditModel (
        @NotBlank(message = "Il tuo commento non può essere vuoto")
        String content
) {
}

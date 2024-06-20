package it.epicode.phronesis.presentationlayer.api.models.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PostReportModel (

        @Positive(message = "Il reportedById non può essere un numero negativo")
        Long reportedById,
        @NotBlank(message = "Devi aggiungere una motivazione alla tua segnalazione")
        String reason,
        @Positive(message = "Il reportedPostId non può essere un numero negativo")
        Long reportedPostId
) {
}

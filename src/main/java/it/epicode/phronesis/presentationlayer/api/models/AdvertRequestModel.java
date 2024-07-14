package it.epicode.phronesis.presentationlayer.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record AdvertRequestModel (

        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "Description is mandatory")
        String description,

        @Positive(message = "devi indicare chi sta creando l'ads")
        Long createdById

) {
}

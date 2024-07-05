package it.epicode.phronesis.presentationlayer.api.models;

import jakarta.validation.constraints.NotBlank;


public record AdvertRequestModel (

        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "Description is mandatory")
        String description

) {
}

package it.epicode.phronesis.businesslayer.dto.post;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;

import java.time.LocalDateTime;

public interface PostResponsePrj extends BaseProjection {
    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    String getTitle();

    String getContent();

    String getImageUrl();

    Long getUserId();
}

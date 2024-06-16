package it.epicode.phronesis.businesslayer.services.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.BaseProjection;
import it.epicode.phronesis.datalayer.entities.User;

import java.time.LocalDateTime;

public interface CommentResponsePrj extends BaseProjection {

    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    User getUser();

    String getContent();
}

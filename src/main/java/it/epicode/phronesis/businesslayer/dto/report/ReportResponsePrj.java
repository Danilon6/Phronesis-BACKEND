package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import it.epicode.phronesis.datalayer.entities.User;

import java.time.LocalDateTime;

public interface ReportResponsePrj extends BaseProjection {

    Long getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    User getReportedBy();

    String getReason();
}

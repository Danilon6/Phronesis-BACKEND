package it.epicode.phronesis.businesslayer.services.dto.report;

import it.epicode.phronesis.datalayer.entities.User;

public interface UserReportResponsePrj extends ReportResponsePrj {

    User getReportedUser();
}

package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.datalayer.entities.User;

public interface UserReportResponsePrj extends ReportResponsePrj {

    UserResponsePartialDTO getReportedUser();
}

package it.epicode.phronesis.businesslayer.services.interfaces.report;

import it.epicode.phronesis.businesslayer.dto.report.UserReportRequestDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponsePrj;

public interface UserReportService extends ReportService<UserReportResponseDTO, UserReportRequestDTO, UserReportResponsePrj> {
}

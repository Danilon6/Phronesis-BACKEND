package it.epicode.phronesis.datalayer.repositories.reportRepositories;

import it.epicode.phronesis.businesslayer.dto.report.PostReportResponsePrj;
import it.epicode.phronesis.datalayer.entities.report.PostReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostReportRepository extends ReportRepository<PostReport, PostReportResponsePrj>{

}

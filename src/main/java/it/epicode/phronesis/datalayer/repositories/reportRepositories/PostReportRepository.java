package it.epicode.phronesis.datalayer.repositories.reportRepositories;

import it.epicode.phronesis.businesslayer.dto.report.PostReportResponsePrj;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.report.PostReport;

import java.util.List;

public interface PostReportRepository extends ReportRepository<PostReport, PostReportResponsePrj>{
    List<PostReport> findAllByReportedPost(Post post);
}

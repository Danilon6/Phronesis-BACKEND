package it.epicode.phronesis.datalayer.repositories.reportRepositories;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import it.epicode.phronesis.businesslayer.dto.report.PostReportResponsePrj;
import it.epicode.phronesis.businesslayer.dto.report.ReportResponsePrj;
import it.epicode.phronesis.datalayer.entities.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReportRepository<T extends Report, B extends BaseProjection> extends
        JpaRepository<T, Long>,
        PagingAndSortingRepository<T, Long> {
    Page<B> findAllByReportedBy(Long id, Pageable p);
    Page<B> findAllBy(Pageable p);
}

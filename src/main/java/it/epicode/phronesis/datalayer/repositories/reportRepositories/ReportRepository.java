package it.epicode.phronesis.datalayer.repositories.reportRepositories;

import it.epicode.phronesis.datalayer.entities.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReportRepository<T extends Report> extends
        JpaRepository<T, Long>,
        PagingAndSortingRepository<T, Long> {
}

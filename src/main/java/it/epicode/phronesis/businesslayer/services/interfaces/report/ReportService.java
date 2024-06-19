package it.epicode.phronesis.businesslayer.services.interfaces.report;


import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService<T extends BaseDTO, A extends BaseDTO, B extends BaseProjection> {

    Page<B> getAllByUserId(Long id, Pageable p);

    Page<B> getAllReports(Pageable p);

    T getById(Long id);

    T save(A e);

    T delete(Long id);

}

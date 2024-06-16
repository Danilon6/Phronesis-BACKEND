package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.services.dto.BaseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T extends BaseDTO, A extends BaseDTO, B extends BaseProjection> {
    Page<B> getAll(Pageable p);

    T getById(Long id);

    T save(A e);

    T update(Long id, T e);

    T delete(Long id);
}

package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.BaseProjection;
import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface CRUDService<T extends BaseDTO, A extends BaseDTO, B extends BaseProjection> {
    Page<B> getAll(Pageable p);

    T getById(Long id);

    T save(A e) throws IOException;

    T update(Long id, T e);

    T delete(Long id);
}

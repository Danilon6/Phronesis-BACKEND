package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.RolesResponsePrj;
import it.epicode.phronesis.businesslayer.dto.RolesRequestDTO;
import it.epicode.phronesis.businesslayer.dto.RolesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesService{

    Page<RolesResponsePrj> getAll(Pageable p);

    RolesResponseDTO save(RolesRequestDTO e);

    RolesResponseDTO delete(Long id);
}

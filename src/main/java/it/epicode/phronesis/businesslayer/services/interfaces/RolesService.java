package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.services.dto.RolesRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.RolesResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.RolesResponsePrj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesService<Roles, RolesDTO>{

    Page<RolesResponsePrj> getAll(Pageable p);

    RolesResponseDTO save(RolesRequestDTO e);

    RolesResponseDTO delete(Long id);
}

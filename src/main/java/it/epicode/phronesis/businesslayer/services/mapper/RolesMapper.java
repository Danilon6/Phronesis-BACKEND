package it.epicode.phronesis.businesslayer.services.mapper;

import it.epicode.phronesis.businesslayer.services.dto.RolesRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.RolesResponseDTO;
import it.epicode.phronesis.datalayer.entities.Roles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    Roles toEntity(RolesRequestDTO RolesRequestDTO);
    RolesResponseDTO toResponse(Roles role);
}

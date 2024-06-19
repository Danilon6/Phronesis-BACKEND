package it.epicode.phronesis.businesslayer.services.impl;



import it.epicode.phronesis.businesslayer.dto.RolesResponseDTO;
import it.epicode.phronesis.businesslayer.dto.RolesResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.RolesService;
import it.epicode.phronesis.businesslayer.dto.RolesRequestDTO;
import it.epicode.phronesis.datalayer.entities.Roles;
import it.epicode.phronesis.datalayer.repositories.RolesRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    Mapper<RolesRequestDTO, Roles> mapRolesRequestDTO2Roles;

    @Autowired
    Mapper<Roles, RolesResponseDTO> mapRoles2RolesResponseDTO;

    @Override
    public RolesResponseDTO save(RolesRequestDTO role) {
            return
                    mapRoles2RolesResponseDTO.map(

                    mapRolesRequestDTO2Roles.map(role)
            );
    }

    @Override
    public Page<RolesResponsePrj> getAll(Pageable p) {
        return rolesRepository.findAllBy(p);
    }


    @Override
    public RolesResponseDTO delete(Long id) {
        var r = rolesRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        rolesRepository.delete(r);
        return mapRoles2RolesResponseDTO.map(r);
    }
}

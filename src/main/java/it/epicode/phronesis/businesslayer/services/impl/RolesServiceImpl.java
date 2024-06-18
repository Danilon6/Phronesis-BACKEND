package it.epicode.phronesis.businesslayer.services.impl;



import it.epicode.phronesis.businesslayer.dto.RolesResponseDTO;
import it.epicode.phronesis.businesslayer.dto.RolesResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.RolesService;
import it.epicode.phronesis.businesslayer.dto.RolesRequestDTO;
import it.epicode.phronesis.datalayer.repositories.RolesRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository roles;



    @Override
    public RolesResponseDTO save(RolesRequestDTO role) {

        return null;
                //rolesMapper.toResponse(rolesMapper.toEntity(role));
    }

    @Override
    public Page<RolesResponsePrj> getAll(Pageable p) {
        return roles.findAllBy(p);
    }


    @Override
    public RolesResponseDTO delete(Long id) {
        var r = roles.findById(id).orElseThrow(()-> new NotFoundException(id));
        roles.delete(r);
        return null;
        //return rolesMapper.toResponse(r);

    }
}

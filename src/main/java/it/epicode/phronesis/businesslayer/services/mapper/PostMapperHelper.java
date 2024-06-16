package it.epicode.phronesis.businesslayer.services.mapper;

import it.epicode.phronesis.businesslayer.services.dto.UserResponseForPostDTO;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapperHelper {

    @Autowired
    private UsersRepository usersRepository;

    @Named("userIdToUser")
    public User userIdToUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

}

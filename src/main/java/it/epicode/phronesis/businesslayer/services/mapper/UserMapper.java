package it.epicode.phronesis.businesslayer.services.mapper;

import it.epicode.phronesis.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.phronesis.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.phronesis.businesslayer.services.dto.UserResponseForPostDTO;
import it.epicode.phronesis.datalayer.entities.User;
import org.mapstruct.Named;

public interface UserMapper {

    User toEntity(RegisterUserDTO userDTO);
    RegisteredUserDTO toResponseDTO(User user);
    LoginResponseDTO toLoginResponseDTO(User user);

    @Named("userIdToUserResponseForPostDTO")
    UserResponseForPostDTO userIdToUserResponseForPostDTO(User user);
}

package it.epicode.phronesis.businesslayer.services.interfaces;


import it.epicode.phronesis.businesslayer.dto.*;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.EmailSendingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.UnsupportedEmailEncodingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    RegisteredUserDTO register(RegisterUserDTO user);

    Optional<LoginResponseDTO> login(String username, String password);

    RegisteredUserDTO getById(long id);

    Page<RegisteredUserPrj> getAll(Pageable p);

    RegisteredUserDTO update(long id, RegisterUserDTO user);

    RegisteredUserDTO delete(Long id);

    RegisteredUserDTO addRole(Long id, String role);

    RegisteredUserDTO removeRole(Long id, String role);

    UserResponsePartialDTO updateProfilePicture(long id, MultipartFile file) throws IOException;

    boolean activateUser(String token);

    void requestNewActivationLink(User u) throws UnsupportedEmailEncodingException, EmailSendingException;

    void banUser(Long userId, String reason) throws UnsupportedEmailEncodingException, EmailSendingException;

    void unbanUser(Long userId) throws UnsupportedEmailEncodingException, EmailSendingException;

}

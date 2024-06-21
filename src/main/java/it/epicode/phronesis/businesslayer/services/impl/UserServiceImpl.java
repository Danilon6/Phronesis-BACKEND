package it.epicode.phronesis.businesslayer.services.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.epicode.phronesis.businesslayer.dto.*;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.UserService;
import it.epicode.phronesis.businesslayer.services.interfaces.email.MailService;
import it.epicode.phronesis.businesslayer.security.ApplicationUserDetailsService;
import it.epicode.phronesis.businesslayer.services.interfaces.image.ImageService;
import it.epicode.phronesis.config.JwtUtils;
import it.epicode.phronesis.datalayer.entities.Roles;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.entities.enums.JwtType;
import it.epicode.phronesis.datalayer.repositories.RolesRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateEmailException;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateUsernameException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.EmailSendingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.UnsupportedEmailEncodingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.user.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ApplicationUserDetailsService userDetailsService;

    @Autowired
    private RolesRepository roles;

    @Autowired
    private Pageable defaultPageable;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    Mapper<RegisterUserDTO, User> mapEntity;

    @Autowired
    Mapper<User, RegisteredUserDTO> mapRegisteredUser;

    @Autowired
    Mapper<User, LoginResponseDTO> mapLogin;

    @Autowired
    Mapper<User, UserResponsePartialDTO> mapUserToPartialResponseDTO;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    ImageService cloudinaryService;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Autowired
    MailService mailService;



    @Override
    public RegisteredUserDTO register(@Valid RegisterUserDTO newUser) {
        var emailDuplicated = usersRepository.findByEmail(newUser.getEmail());
        var usernameDuplicated = usersRepository.findOneByUsername(newUser.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(newUser.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(newUser.getUsername());
        } else {
            try {

                var userEntity = mapEntity.map(newUser);

                cloudinaryService.verifyMaxSizeOfFile(newUser.getProfilePictureFile());
                    // Carica l'immagine su Cloudinary
                    var url = (String) cloudinary.uploader().upload(newUser.getProfilePictureFile().getBytes(), ObjectUtils.emptyMap()).get("url");
                    userEntity.setProfilePicture(url);

                var p = encoder.encode(newUser.getPassword());
                log.info("Password encrypted: {}", p);
                userEntity.setPassword(p);
                        var totalUsers = this.getAll(defaultPageable);

                        if (totalUsers.getTotalElements() == 0)
                        {
                            userEntity.getRoles().add(
                                    Roles.builder()
                                            .withRoleType("ADMIN")
                                            .build()
                            );

                        } else {
                            userEntity.getRoles().add(
                                    Roles.builder()
                                            .withRoleType("USER")
                                            .build()
                            );
                        }
                        var uEntity = usersRepository.save(userEntity);
                    var u = mapRegisteredUser.map(uEntity);
                    var token = jwt.generateToken(u.getId());

                       mailService.sendMail(uEntity, token);
                       return u;
            } catch (Exception e) {
                log.error(String.format("Exception saving user %s", usersRepository), e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    public boolean activateUser(String token) {
        try {
            if (jwt.isTokenValid(token, JwtType.VALIDATION_LINK.name())) {
                var id = Long.valueOf(jwt.getSubjectFromToken(token, JwtType.VALIDATION_LINK.name()));

                var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

                if (!user.isEnabled()) {
                    user.setEnabled(true);
                    usersRepository.save(user);
                    return true;
                }
                throw new UserIsAlreadyEnabledException(user.getUsername());
            }
            return false;
        }catch (ExpiredJwtException e) {
            throw new UserActivationException("Token expired,required new token");
        } catch (UnsupportedJwtException e) {
            throw new UserActivationException("Format token not supported");
        } catch (MalformedJwtException e) {
            throw new UserActivationException("Invalid token format");
        } catch (JwtException e) {
            throw new UserActivationException("Invalid JWT token");
        }
    }

    @Override
    public void requestNewActivationLink(User u) throws UnsupportedEmailEncodingException, EmailSendingException {
        String token = jwt.generateToken(u.getId());

        mailService.sendMailNewVerificationLink(u,token);
    }


    @Override
    public Optional<LoginResponseDTO> login(String username, String password) {
        try {
            //SI EFFETTUA IL LOGIN
            //SI CREA UNA AUTENTICAZIONE OVVERO L'OGGETTO DI TIPO AUTHENTICATION
            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            //SI CREA UN CONTESTO DI SICUREZZA CHE SARA UTILIZZATO IN PIU OCCASIONI
            SecurityContextHolder.getContext().setAuthentication(a);


            var dto = mapLogin.map(usersRepository.findOneByUsername(username).orElseThrow());

            //UTILIZZO DI JWTUTILS PER GENERARE IL TOKEN UTILIZZANDO UNA AUTHENTICATION E LO ASSEGNA ALLA LOGINRESPONSEDTO
            dto.setToken(jwt.generateToken(a));

            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        } catch(DisabledException e) {
            log.error("Authentication failed due to user not enabled", e);
            throw new UserNotEnabledException(username, password);
        }
        catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw new InvalidLoginException(username, password);
        }
    }

    @Override
    public RegisteredUserDTO getById(long id) {
        return mapRegisteredUser.map(usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id)));
    }

    @Override
    public Page<RegisteredUserPrj> getAll(Pageable p) {
        return usersRepository.findAllBy(p);
    }

    //Nel frontend potrei fornire all utente solo un modulo epr cambiare i dati tranne l'immagine
    //per cambiare l'immagine lo mandiamo ad un altro modulo con endpoint diverso
    @Override
    public RegisteredUserDTO update(long id, UpdateUserDTO user) {
        var u = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        var usernameDuplicated = usersRepository.findOneByUsername(user.getUsername());
        var emailDuplicated = usersRepository.findByEmail(user.getEmail());
        //verifico che l'email cambiata non appartenga a nessun'altro utente
        if (emailDuplicated.isPresent() && !Objects.equals(emailDuplicated.get().getEmail(), user.getEmail())) {
            throw new DuplicateEmailException(user.getEmail());
        //verifico che lo username cambiato non appartenga a nessun'altro utente
        } else if (usernameDuplicated.isPresent() && !Objects.equals(usernameDuplicated.get().getUsername(), user.getUsername())) {
            throw new DuplicateUsernameException(user.getUsername());
        } else {
            try {
                BeanUtils.copyProperties(user, u);

                return mapRegisteredUser.map(usersRepository.save(u));

            }catch (Exception ex) {
                throw new RuntimeException("Exception in updating user");
            }
        }
    }


    @Override
    public RegisteredUserDTO delete(Long id) {
        try {
            var u = usersRepository.findById(id).orElseThrow();
            //estraggo il publicId per l'eliminazione dell'imamgine che era associata all'utente
            var publicID = cloudinaryService.extractPublicIdFromUrl(u.getProfilePicture());
            //elimino l'immagine
            cloudinaryService.deleteImage(publicID);
            //elimino l'utente
            usersRepository.delete(u);
            return mapRegisteredUser.map(u);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find user with id = %s", id), e);
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            log.error(String.format("Error deleting user with id = %s", id), e);
            throw new RuntimeException();
        }
    }

    @Override
    public RegisteredUserDTO addRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere non esiste");
        } else if (user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere è gia presente");
        } else {
            user.getRoles().add(roleEntity.get());
            return mapRegisteredUser.map(usersRepository.save(user));
        }
    }

    @Override
    public RegisteredUserDTO removeRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));


        // Verifica che il ruolo esista
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non esiste");
        }

        // Verifica che l'utente abbia il ruolo specificato
        if (!user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non è presente");
        }

        // Verifica specifica per il ruolo ADMIN
        if (Objects.equals(role, "ADMIN")) {
            var adminUsers = usersRepository.findByRoles_RoleType(role);

            // Controlla che ci siano altri admin
            if (adminUsers.size() == 1) {
                throw new RuntimeException("Deve esistere almeno un admin sul database");
            }
        }

        // Verifica che l'utente abbia almeno un altro ruolo oltre a quello da rimuovere
        if (user.getRoles().size() == 1 && user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Un utente deve avere almeno un ruolo");
        }

        // Rimuovi il ruolo
        user.getRoles().remove(roleEntity.get());

        // Salva le modifiche e ritorna l'utente aggiornato
        return mapRegisteredUser.map(usersRepository.save(user));

    }

    @Override
    public UserResponsePartialDTO updateProfilePicture(long id, MultipartFile file) throws IOException {
        return cloudinaryService.updateProfilePicture(id, file);
    }

    @Override
    public void banUser(Long id, String reason) throws UnsupportedEmailEncodingException, EmailSendingException {
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        if (user.isBanned()) {
            throw new UserAlreadyBannedException(user.getUsername());
        }
            user.setBanned(true);
            usersRepository.save(user);
            mailService.sendBannedEmail(user, reason);
    }

    @Override
    public void unbanUser(Long id) throws UnsupportedEmailEncodingException, EmailSendingException {
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (!user.isBanned()) {
            throw new UserAlreadyUnbannedException(user.getUsername());
        }
            user.setBanned(false);
            usersRepository.save(user);
            mailService.sendUnbannedEmail(user);
    }

    public Page<RegisteredUserPrj> getAllBannedUsers(Pageable p){
        return usersRepository.findAllByBanned(true, p);
    }
}

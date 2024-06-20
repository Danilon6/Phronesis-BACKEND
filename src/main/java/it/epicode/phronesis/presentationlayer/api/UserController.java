package it.epicode.phronesis.presentationlayer.api;



import io.jsonwebtoken.JwtException;
import it.epicode.phronesis.businesslayer.dto.*;
import it.epicode.phronesis.businesslayer.services.interfaces.UserService;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.EmailSendingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.UnsupportedEmailEncodingException;
import it.epicode.phronesis.presentationlayer.api.models.LoginModel;
import it.epicode.phronesis.presentationlayer.api.models.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<Page<RegisteredUserPrj>> getAllUsers (Pageable p) {
        var allUsers = userService.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allUsers.getTotalElements()));
        return new ResponseEntity<>(allUsers, headers, HttpStatus.OK);
    }

    @GetMapping("banned")
    public ResponseEntity<Page<RegisteredUserPrj>> getAllBannedUsers (Pageable p) {
        var allBannedUsers = userService.getAllBannedUsers(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allBannedUsers.getTotalElements()));
        return new ResponseEntity<>(allBannedUsers, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> getUser (@PathVariable Long id) {
        var u = userService.getById(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }



    @PostMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam String token) {
        try {
            if (userService.activateUser(token)) {
                return ResponseEntity.ok("Account activated successfully!");
            } else {
                return new ResponseEntity<>("Failed to activate account!", HttpStatus.EXPECTATION_FAILED);
            }
        } catch (JwtException e) {
            return new ResponseEntity<>("Invalid activation link!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestParam("firstName") String firstName,
                                                      @RequestParam("lastName") String lastName,
                                                      @RequestParam("username") String username,
                                                      @RequestParam("email") String email,
                                                      @RequestParam("password") String password,
                                                      @RequestParam("bio") String bio,
                                                      @RequestParam("profilePictureFile") MultipartFile profilePictureFile) {

        var registeredUser = userService.register(
                RegisterUserDTO.builder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withUsername(username)
                        .withEmail(email)
                        .withBio(bio)
                        .withProfilePictureFile(profilePictureFile)
                        .withPassword(password)
                .build());

        return  new ResponseEntity<> (registeredUser, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw  new ApiValidationException(validator.getAllErrors());
        }
        return new ResponseEntity<>(userService.login(model.username(), model.password()).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/request-new-token")
    public ResponseEntity<String> requestNewActivationToken(@RequestParam String email) throws UnsupportedEmailEncodingException, EmailSendingException {
        // Verifica se l'utente esiste e non è già attivo
        var u = usersRepository.findByEmail(email);
        if (u.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else if (u.get().isEnabled()) {
            return new ResponseEntity<>("Account already activated", HttpStatus.BAD_REQUEST);
        }

        userService.requestNewActivationLink(u.get());

        return new ResponseEntity<>("A new activation link has been sent to your email.", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> updateUser (
            @PathVariable Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("bio") String bio,
            @RequestParam("profilePictureFile") MultipartFile profilePictureFile
    ){
        var u = userService.update(id, RegisterUserDTO.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withUsername(username)
                .withEmail(email)
                .withBio(bio)
                .withProfilePictureFile(profilePictureFile)
                .withPassword(password)
                .build());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PatchMapping("{id}/addToRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RegisteredUserDTO> addUserRole (
            @PathVariable Long id,
            @RequestParam("role") String role
    ){
        var u = userService.addRole(id, role);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PatchMapping("{id}/removeToRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RegisteredUserDTO> removeUserRole (
            @PathVariable Long id,
            @RequestParam("role") String role
    ){
        var u = userService.removeRole(id, role);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> deleteUser (
            @PathVariable Long id
    ) {
        var e = userService.delete(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PatchMapping("/{id}/avatar")
    public UserResponsePartialDTO updateProfilePicture(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            return userService.updateProfilePicture(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<String> banUser(
            @PathVariable Long id,
            @RequestParam String reason) throws UnsupportedEmailEncodingException, EmailSendingException {
        userService.banUser(id, reason);
        return ResponseEntity.ok("User banned successfully.");
    }

    @PutMapping("/{id}/unban")
    public ResponseEntity<String> unbanUser(
            @PathVariable Long id) throws UnsupportedEmailEncodingException, EmailSendingException {
        userService.unbanUser(id);
        return ResponseEntity.ok("User unbanned successfully.");
    }


}

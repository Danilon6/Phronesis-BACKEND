package it.epicode.phronesis.businesslayer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO extends BaseDTO {
    @NotBlank(message = "Il tuo nome non può essere vuoto")
    private String firstName;

    @NotBlank(message = "Il tuo cognome non può essere vuoto")
    private String lastName;

    @NotBlank(message = "Lo username non può contenere solo spazi vuoti")
    @Size(max = 50, message ="Il tuo username è troppo lungo max 50 caratteri")
    private String username;

    @Email(message = "Inserisci una email valida")
    private String email;

    @NotBlank(message = "La password non può contenere solo spazi vuoti")
    @Size(max = 125, message ="La password è troppo lunga max 20 caratteri")
    private String password;

    @NotNull(message = "Devi caricare una foto profilo")
    private MultipartFile profilePictureFile;

    @NotBlank(message = "Inserisci una breve descrizione di te")
    private String bio;
}

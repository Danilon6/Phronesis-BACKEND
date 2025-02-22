package it.epicode.phronesis.businesslayer.dto;


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
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private MultipartFile profilePictureFile;
    private String bio;
}

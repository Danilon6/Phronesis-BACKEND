package it.epicode.phronesis.businesslayer.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDTO {
    RegisteredUserDTO user;
    String token;

    @Builder(setterPrefix = "with")
    public LoginResponseDTO(RegisteredUserDTO user, String token) {
        this.user = user;
        this.token = token;
    }
}



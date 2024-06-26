package it.epicode.phronesis.businesslayer.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginAndRegisterResponseDTO {
    RegisteredUserDTO user;
    String token;

    @Builder(setterPrefix = "with")
    public LoginAndRegisterResponseDTO(RegisteredUserDTO user, String token) {
        this.user = user;
        this.token = token;
    }
}



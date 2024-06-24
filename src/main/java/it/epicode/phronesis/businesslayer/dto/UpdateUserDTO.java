package it.epicode.phronesis.businesslayer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UpdateUserDTO extends BaseDTO{

    private String firstName;

    private String lastName;

    private String email;

    private String bio;
}

package it.epicode.phronesis.businesslayer.dto;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserResponsePartialDTO extends BaseDTO {

    Long id;

    String username;

    String profilePicture;
}

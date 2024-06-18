package it.epicode.phronesis.businesslayer.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UpdateUserRequestDto extends BaseDTO{
    String firstName;
    String lastName;
}

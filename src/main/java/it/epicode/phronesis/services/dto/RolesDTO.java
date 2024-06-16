package it.epicode.phronesis.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RolesDTO extends BaseDTO{
    String roleType;
}

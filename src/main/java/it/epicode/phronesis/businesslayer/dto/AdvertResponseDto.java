package it.epicode.phronesis.businesslayer.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AdvertResponseDto extends BaseDTO{
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
}

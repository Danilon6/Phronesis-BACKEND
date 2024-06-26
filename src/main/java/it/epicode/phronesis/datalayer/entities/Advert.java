package it.epicode.phronesis.datalayer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Advertisements")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Advert extends BaseEntity {
    private String title;
    private String description;
    private String imageUrl;  // URL dell'immagine

}

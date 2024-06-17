package it.epicode.phronesis.businesslayer.services.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.services.dto.UserResponsePartialDTO;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import lombok.*;

import java.time.LocalDateTime;

//Questa risposta di like preferiti e commenti non include nessun riferimento al post perchè è la risposat che sarà utilizzata
//nella hmem quando l'utente ha necessità di consocere solamente chi ha messo like e chi ha fatto il commento
//per quanto riguarda i preferiti invece facciamo un'altra rispsota dove inseriamo solament eil psot perche noi
//recuepriamo i preferiti in base all'utente quindi il frontend gia consoce
// l'utente che ha aggiunto il post ai preferiti, è inutile restituirglielo nuovamente
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserPostInteractionResponseDTO extends BaseDTO {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserResponsePartialDTO user;

}

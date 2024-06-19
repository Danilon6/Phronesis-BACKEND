package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;


import it.epicode.phronesis.businesslayer.dto.BaseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;

public interface UserPostInteractionService<T extends BaseDTO, A extends BaseDTO>{

    T  save(A e);

    T  delete(Long id);
}

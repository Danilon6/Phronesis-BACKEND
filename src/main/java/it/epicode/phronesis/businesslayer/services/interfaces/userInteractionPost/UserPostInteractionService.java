package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;


import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionResponseDTO;

public interface UserPostInteractionService{

    UserPostInteractionResponseDTO save(UserPostInteractionRequestDTO e);

    UserPostInteractionResponseDTO delete(Long id);
}

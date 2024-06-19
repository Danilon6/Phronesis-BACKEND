package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;

import java.util.List;

public interface LikeService extends UserPostInteractionService<UserPostInteractionResponseDTO, UserPostInteractionRequestDTO> {

     List<UserPostInteractionResponsePrj> getAllByPostId(Long id);
     List<UserPostInteractionResponsePrj> getAllByUserId(Long id);
}

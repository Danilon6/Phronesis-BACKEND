package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.*;

import java.util.List;

public interface LikeService extends UserPostInteractionService<UserPostInteractionResponseDTO, UserPostInteractionRequestDTO> {

     List<UserPostInteractionResponsePrj> getAllByPostId(Long id);
     List<UserPostInteractionResponseWithPostPrj> getAllByUserId(Long id);
}

package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionResponseDTO;

public interface LikeService extends UserPostInteractionService {

    UserPostInteractionResponseDTO getAllByPostId(Long id);
}

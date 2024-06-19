package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService extends UserPostInteractionService<FavoriteResponseDTO, UserPostInteractionRequestDTO> {

    Page<UserPostInteractionResponsePrj> getAllByUserId(Pageable p, Long id);
}

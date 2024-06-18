package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService extends UserPostInteractionService {

    Page<UserPostInteractionResponseDTO> getAllByUserId(Pageable p, Long id);
}

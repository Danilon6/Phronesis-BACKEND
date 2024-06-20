package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService extends UserPostInteractionService<FavoriteResponseDTO, UserPostInteractionRequestDTO> {

    Page<FavoriteResponsePrj> getAllByUserId(Pageable p, Long id);
}

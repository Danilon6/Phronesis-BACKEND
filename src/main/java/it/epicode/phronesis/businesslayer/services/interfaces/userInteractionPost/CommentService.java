package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.CRUDService;

import java.util.List;

public interface CommentService extends UserPostInteractionService<CommentResponseDTO, CommentRequestDTO> {

    List<CommentResponsePrj> getAllByPostId(Long id);

    CommentResponseDTO getById(Long id);

    CommentResponseDTO update(Long id, CommentRequestDTO e);
}

package it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost;

import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.CRUDService;

public interface CommentService extends CRUDService<CommentResponseDTO, CommentRequestDTO, CommentResponsePrj> {
}

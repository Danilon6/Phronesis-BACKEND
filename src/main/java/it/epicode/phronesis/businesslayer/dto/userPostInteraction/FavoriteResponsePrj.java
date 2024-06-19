package it.epicode.phronesis.businesslayer.dto.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;

public interface FavoriteResponsePrj extends UserPostInteractionResponsePrj{

    PostResponseDTO getPost();
}

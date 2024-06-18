package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;

public interface PostService extends CRUDService<PostResponseDTO, PostRequestDTO, PostResponsePrj>{
}

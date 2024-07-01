package it.epicode.phronesis.businesslayer.services.interfaces;

import it.epicode.phronesis.businesslayer.dto.post.PostEditRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface PostService {
    Page<PostResponsePrj> getAll(Pageable p);
    Page<PostResponsePrj> getAllByUserId(Pageable p, Long id);
    PostResponseDTO getById(Long id);
    PostResponseDTO save(PostRequestDTO e) throws IOException;
    PostResponseDTO update(Long id, PostEditRequestDTO e) throws IOException;
    PostResponseDTO delete(Long id);
}

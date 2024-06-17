package it.epicode.phronesis.businesslayer.services.impl;

import it.epicode.phronesis.businesslayer.services.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.post.PostResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.PostService;
import it.epicode.phronesis.businesslayer.services.mapper.post.PostMapper;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository post;

    @Autowired
    PostMapper postMapper;

    @Override
    public Page<PostResponsePrj> getAll(Pageable p) {
        return null;
    }

    @Override
    public PostResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public PostResponseDTO save(PostRequestDTO e) {
        var titleDuplicate = post.findByTitle(e.getTitle());
        if (titleDuplicate.isPresent())
            throw new DuplicateTitleException(e.getTitle());

        //con il postMapper converto il PostRequestDTO (e) in una entity Post
        // con il repository (post) salvo la entity
        // creo una variabile p che contiene il risultato del salvataggio
        var p = post.save(postMapper.toEntity(e));

        //con il mapper converto il post ottenuto in precedenza in un PostResponseDTO e lo restituisco
        return postMapper.toResponseDTO(p);
    }

    @Override
    public PostResponseDTO update(Long id, PostResponseDTO e) {
        return null;
    }

    @Override
    public PostResponseDTO delete(Long id) {
        return null;
    }
}

package it.epicode.phronesis.businesslayer.services.mapper.post;

import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

public class PostMapperHelper {

    @Autowired
    private PostRepository postRepository;

    @Named("postIdToPost")
    public Post postIdToPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }
}

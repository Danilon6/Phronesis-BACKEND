package it.epicode.phronesis.businesslayer.services.impl.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseWithPostPrj;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.LikeService;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.LikeRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    Mapper<Like, UserPostInteractionResponseDTO> mapLikeEntityToUserPostInteractionResponseDTO;



    @Override
    public List<UserPostInteractionResponsePrj> getAllByPostId(Long id) {
        return likeRepository.findByPostId(id);
    }

    @Override
    public List<UserPostInteractionResponseWithPostPrj> getAllByUserId(Long id) {
        return likeRepository.findByUserId(id);
    }

    @Override
    public UserPostInteractionResponseDTO save(UserPostInteractionRequestDTO e) {
        var user = usersRepository.findById(e.getUserId()).orElseThrow(()-> new NotFoundException(e.getUserId()));
        var post = postRepository.findById(e.getPostId()).orElseThrow(()-> new NotFoundException(e.getPostId()));
        var like = Like.builder()
                .withUser(user)
                .withPost(post)
                .build();
        var likeSaved = likeRepository.save(like);
        post.getLikes().add(likeSaved);
        return mapLikeEntityToUserPostInteractionResponseDTO.map(likeRepository.save(likeSaved));
    }

    @Override
    public UserPostInteractionResponseDTO delete(Long id) {
        var like = likeRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        likeRepository.delete(like);
        return mapLikeEntityToUserPostInteractionResponseDTO.map(like);
    }
}

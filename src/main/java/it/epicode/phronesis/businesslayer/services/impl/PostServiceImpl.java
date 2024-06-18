package it.epicode.phronesis.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.PostService;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.CommentRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.LikeRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateTitleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    Mapper<Like, UserPostInteractionResponseDTO> mapLikeToResponseDTO;

    @Autowired
    Mapper<Comment, CommentResponseDTO> mapCommentToResponseDTO;

    @Autowired
    Mapper<User, UserResponsePartialDTO> mapUserToPartialResponseDTO;

    @Autowired
    Mapper<PostRequestDTO, Post> mapPostRequestDTO2Post;

    @Autowired
    Mapper<Post, PostResponseDTO> mapPost2PostResponseDTO;


    @Override
    public Page<PostResponsePrj> getAll(Pageable p) {
        return postRepository.findAllBy(p);
    }

    @Override
    public PostResponseDTO getById(Long id) {
        var postEntity =  postRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        var comments = commentRepository.findByPostId(postEntity.getId())
                .stream()
                .map(c-> mapCommentToResponseDTO.map(c))
                .toList();

        var likes = likeRepository.findByPostId(postEntity.getId())
                .stream()
                .map(l -> mapLikeToResponseDTO.map(l))
                .toList();

        var mapperCustom = PostToPostResponseDTOMapperImpl.builder()
                .withComments(comments)
                .withLikes(likes)
                .build();
        return mapperCustom.map(postEntity);
    }

    @Override
    public PostResponseDTO save(PostRequestDTO e) throws IOException {
        //verifico che il titolo del post non sia duplicato
        var titleDuplicate = postRepository.findByTitle(e.getTitle());
        //verifico che lo user esista(quetso contorllo potrebbe essere inutile perche se uno user sta facendo il post per forza deve esistere)
        var user = usersRepository.findById(e.getUserId()).orElseThrow(()-> new NotFoundException(e.getUserId()));
        if (titleDuplicate.isPresent())
            throw new DuplicateTitleException(e.getTitle());



        //converto la request in una entity
        var postEntity = mapPostRequestDTO2Post.map(e);

        //setto lo user
        postEntity.setUser(user);

        //verifico se nella request è stata fornita una immagine di conseguenza la salvo su cloudinary e la setto come imageUrl
        if (e.getImageFile()!= null && !e.getImageFile().isEmpty()) {
            var file = e.getImageFile();
            var url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            postEntity.setImageUrl(url);
        }

        //salvo l'entità

        var postSaved = postRepository.save(postEntity);

        //converto il post appena salavto in una response
        //i like e i commenti saranno automaticamente null
        return mapPost2PostResponseDTO.map(postSaved);

    }

    @Override
    public PostResponseDTO update(Long id, PostResponseDTO e) {
        return null;
    }

    @Override
    public PostResponseDTO delete(Long id) {
        try {
            var p = postRepository.findById(id).orElseThrow();
            postRepository.delete(p);
            return mapPost2PostResponseDTO.map(p);
        } catch (NoSuchElementException ex) {
            log.error(String.format("Cannot find post with id = %s", id), ex);
            throw new RuntimeException("Cannot find post...");
        } catch (Exception ex) {
            log.error(String.format("Error deleting post with id = %s", id), ex);
            throw new RuntimeException();
        }
    }
}

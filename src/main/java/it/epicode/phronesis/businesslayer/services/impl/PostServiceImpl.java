package it.epicode.phronesis.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostEditRequestDTO;
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
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.FavoriteRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.LikeRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.datalayer.repositories.reportRepositories.PostReportRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.exceptions.duplicated.DuplicateTitleException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    PostReportRepository postReportRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    Mapper<Like, UserPostInteractionResponseDTO> mapLikeToResponseDTO;

    @Autowired
    Mapper<Comment, CommentResponseDTO> mapCommentToResponseDTO;

    @Autowired
    Mapper<User, UserResponsePartialDTO> mapUserToPartialResponseDTO;

    @Autowired
    Mapper<PostRequestDTO, Post> mapPostRequestDTOToPost;

    @Autowired
    Mapper<Post, PostResponseDTO> mapPostToPostResponseDTO;


    @Override
    public Page<PostResponsePrj> getAll(Pageable p) {
        return postRepository.findAllBy(p);
    }

    @Override
    public Page<PostResponsePrj> getAllByUserId(Pageable p, Long id) {
        return postRepository.findAllByUserId(p, id);
    }

    @Override
    public PostResponseDTO getById(Long id) {
        var postEntity =  postRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        var comments = commentRepository.findByPostId(postEntity.getId());
        var likes = likeRepository.findByPostId(postEntity.getId());

        var postResponse = mapPostToPostResponseDTO.map(postEntity);

        postResponse.setComments(comments);
        postResponse.setLikes(likes);

        return postResponse;
    }

    @Override
    public PostResponseDTO save(PostRequestDTO e) throws IOException {
        //verifico che il titolo del post non sia duplicato
        var titleDuplicate = postRepository.findByTitle(e.getTitle());
        if (titleDuplicate.isPresent())
            throw new DuplicateTitleException(e.getTitle());

        var user = usersRepository.findById(e.getUserId()).orElseThrow(()-> new NotFoundException(e.getUserId()));

        //converto la request in una entity
        var postEntity = mapPostRequestDTOToPost.map(e);
        //setto lo user
        postEntity.setUser(user);
        //salvo l'entità
        var postSaved = postRepository.save(postEntity);

        //converto il post appena salvato in una response
        //i like e i commenti saranno automaticamente null

        return mapPostToPostResponseDTO.map(postSaved);

    }

    @Override
    public PostResponseDTO update(Long id, PostEditRequestDTO e) throws IOException {
        var post = postRepository.findById(id).orElseThrow(()-> new NotFoundException(id));

        BeanUtils.copyProperties(e, post);

        return mapPostToPostResponseDTO.map(postRepository.save(post));

    }

    @Transactional
    @Override
    public PostResponseDTO delete(Long id) {
        try {
            var p = postRepository.findById(id).orElseThrow();
            favoriteRepository.deleteByPost(p);
            postReportRepository.deleteAll(postReportRepository.findAllByReportedPost(p));
            postRepository.delete(p);
            return mapPostToPostResponseDTO.map(p);
        } catch (NoSuchElementException ex) {
            log.error(String.format("Cannot find post with id = %s", id), ex);
            throw new RuntimeException("Cannot find post...");
        } catch (Exception ex) {
            log.error(String.format("Error deleting post with id = %s", id), ex);
            throw new RuntimeException();
        }
    }


}

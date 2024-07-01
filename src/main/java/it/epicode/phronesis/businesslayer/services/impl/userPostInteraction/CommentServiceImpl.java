package it.epicode.phronesis.businesslayer.services.impl.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.CommentResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.CommentService;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.CommentRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.CommentRequestEditModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    Mapper<Comment, CommentResponseDTO> mapCommentEntityToCommentResponseDTO;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<CommentResponsePrj> getAllByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    @Override
    public CommentResponseDTO getById(Long id) {
        var comment = commentRepository.findById(id).orElseThrow(()->new NotFoundException(id));

        return mapCommentEntityToCommentResponseDTO.map(comment);
    }

    @Override
    public CommentResponseDTO save(CommentRequestDTO e) {
        var user = usersRepository.findById(e.getUserId()).orElseThrow(()-> new NotFoundException(e.getUserId()));
        var post = postRepository.findById(e.getPostId()).orElseThrow(()-> new NotFoundException(e.getPostId()));
        var comment = Comment.builder()
                .withPost(post)
                .withUser(user)
                .withContent(e.getContent())
                .build();

        var commentSaved = commentRepository.save(comment);
        post.getComments().add(commentSaved);
        return mapCommentEntityToCommentResponseDTO.map(commentSaved);
    }

    @Override
    public CommentResponseDTO update(Long id, String content){
        var comment = commentRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        comment.setContent(content);
         return mapCommentEntityToCommentResponseDTO.map(commentRepository.save(comment));


    }

    @Override
    public CommentResponseDTO delete(Long id) {
        var comment = commentRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        commentRepository.delete(comment);
        return mapCommentEntityToCommentResponseDTO.map(comment);
    }
}

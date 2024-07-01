package it.epicode.phronesis.presentationlayer.api.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.*;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.CommentService;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.CommentRequestEditModel;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.CommentRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("post/{id}")
    public ResponseEntity<List<CommentResponsePrj>> getAllCommentsByPostId(@PathVariable Long id) {
        var comment = commentService.getAllByPostId(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long id) {
        var comment = commentService.getById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody @Validated CommentRequestModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var comment = commentService.save(
                CommentRequestDTO.commentRequestBuilder()
                        .withUserId(model.userId())
                        .withPostId(model.postId())
                        .withContent(model.content())
                        .build());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }


    @PatchMapping("{id}")
    public ResponseEntity<CommentResponseDTO> update (@PathVariable Long id, @RequestBody @Validated CommentRequestEditModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var comment = commentService.update(id, model.content());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<UserPostInteractionResponseDTO> removeComment(@PathVariable Long id) {
        var deletedComment = commentService.delete(id);
        return new ResponseEntity<>(deletedComment, HttpStatus.OK);
    }

}

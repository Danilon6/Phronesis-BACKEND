package it.epicode.phronesis.presentationlayer.api.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.LikeService;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.LikeRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.UserPostInteractionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/likes")
@Slf4j
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping("post/{id}")
    public ResponseEntity<List<UserPostInteractionResponsePrj>> getAllLikesByPostId (@PathVariable Long id) {
        var likes = likeService.getAllByPostId(id);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserPostInteractionResponseDTO> addLike(@RequestBody @Validated UserPostInteractionModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var newLike = likeService.save(
                UserPostInteractionRequestDTO.builder()
                        .withUserId(model.userId())
                        .withPostId(model.postId())
                        .build()
        );
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserPostInteractionResponseDTO> removeLike(@PathVariable Long id) {
        var deletedLike = likeService.delete(id);
        return new ResponseEntity<>(deletedLike, HttpStatus.OK);
    }

}

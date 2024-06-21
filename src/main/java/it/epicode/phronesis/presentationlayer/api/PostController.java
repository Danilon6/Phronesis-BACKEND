package it.epicode.phronesis.presentationlayer.api;

import it.epicode.phronesis.businesslayer.dto.RegisterUserDTO;
import it.epicode.phronesis.businesslayer.dto.RegisteredUserDTO;
import it.epicode.phronesis.businesslayer.dto.RegisteredUserPrj;
import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.PostService;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.PostRequestModel;
import it.epicode.phronesis.presentationlayer.api.models.RegisterUserModel;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.CommentRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/post")
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<Page<PostResponsePrj>> getAllPosts (Pageable p) {
        var allPosts = postService.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allPosts.getTotalElements()));
        return new ResponseEntity<>(allPosts, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDTO> getPostById (@PathVariable Long id) {
        var p = postService.getById(id);
        return new ResponseEntity<>(p, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> addNewPost(
            @RequestBody @Validated PostRequestModel model, BindingResult validator) throws IOException {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var postResponseDTO = postService.save(
                PostRequestDTO.builder()
                        .withTitle(model.title())
                        .withContent(model.content())
                        .withUserId(model.userId())
                        .build());
        return  new ResponseEntity<> (postResponseDTO, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<PostResponseDTO> updateUser (
            @PathVariable Long id, @RequestBody @Validated PostRequestModel model, BindingResult validator) throws IOException {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var postResponseDTO = postService.update( id,
                PostRequestDTO.builder()
                        .withTitle(model.title())
                        .withContent(model.content())
                        .withUserId(model.userId())
                        .build());
        return new ResponseEntity<>(postResponseDTO, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<PostResponseDTO> deleteUser (
            @PathVariable Long id
    ) {
        var p = postService.delete(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}

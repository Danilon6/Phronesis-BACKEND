package it.epicode.phronesis.presentationlayer.api.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.FavoriteService;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.userInteractionPost.UserPostInteractionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorites")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;



    @GetMapping("user/{id}")
    public ResponseEntity<Page<FavoriteResponsePrj>> getAllFavoritesByUserId (@PathVariable Long id, Pageable p) {
        var favorites = favoriteService.getAllByUserId(p, id);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FavoriteResponseDTO> addToFavorite(@RequestBody @Validated UserPostInteractionModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var favorite = favoriteService.save(
                UserPostInteractionRequestDTO.builder()
                        .withUserId(model.userId())
                        .withPostId(model.postId())
                        .build()
        );
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FavoriteResponseDTO> removeFavorites(@PathVariable Long id) {
        var deletedFavorite = favoriteService.delete(id);
        return new ResponseEntity<>(deletedFavorite, HttpStatus.OK);
    }
}

package it.epicode.phronesis.businesslayer.services.impl.userPostInteraction;

import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.FavoriteResponsePrj;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.userInteractionPost.FavoriteService;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Favorite;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UserPostInteractionRepositories.FavoriteRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    Mapper<Favorite, FavoriteResponseDTO> mapFavoriteEntityToFavoriteResponseDTO;
    @Override
    public Page<FavoriteResponsePrj> getAllByUserId(Pageable p, Long id) {
        return favoriteRepository.findAllByUserId(p, id);
    }

    @Override
    public FavoriteResponseDTO save(UserPostInteractionRequestDTO e) {
        var user = usersRepository.findById(e.getUserId()).orElseThrow(()-> new NotFoundException(e.getUserId()));
        var post = postRepository.findById(e.getPostId()).orElseThrow(()-> new NotFoundException(e.getPostId()));
        var favorite = Favorite.builder()
                .withUser(user)
                .withPost(post)
                .build();

        var favoriteSaved = favoriteRepository.save(favorite);
        return mapFavoriteEntityToFavoriteResponseDTO.map(favoriteSaved);
    }

    @Override
    public FavoriteResponseDTO delete(Long id) {
        var favorite = favoriteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        favoriteRepository.delete(favorite);
        return mapFavoriteEntityToFavoriteResponseDTO.map(favorite);
    }
}

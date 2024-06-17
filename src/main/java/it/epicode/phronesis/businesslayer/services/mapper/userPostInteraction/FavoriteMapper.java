package it.epicode.phronesis.businesslayer.services.mapper.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.FavoriteResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.businesslayer.services.mapper.post.PostMapperHelper;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapperHelper.class, PostMapperHelper.class})
public interface FavoriteMapper {

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(source = "postId", target = "post", qualifiedByName = "postIdToPost")
    Favorite toEntity(UserPostInteractionRequestDTO userPostInteractionRequestDTO);
    @Mapping(source = "post", target = "post", qualifiedByName = "postToResponseDTO")
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserResponsePartialDTO" )
    @Named("UserPostInteractionToResponseDTO")
    FavoriteResponseDTO toResponseDTO(Favorite favorite);
}

package it.epicode.phronesis.businesslayer.services.mapper.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.UserPostInteractionResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.businesslayer.services.mapper.post.PostMapperHelper;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapperHelper.class, PostMapperHelper.class})
public interface LikeMapper {

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(source = "postId", target = "post", qualifiedByName = "postIdToPost")
    Like toEntity(UserPostInteractionRequestDTO userPostInteractionRequestDTO);
    @Mapping(target = "post", ignore = true)
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserResponsePartialDTO" )
    @Named("LikeToResponseDTO")
    UserPostInteractionResponseDTO toResponseDTO(Like like);
}

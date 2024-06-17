package it.epicode.phronesis.businesslayer.services.mapper.userPostInteraction;

import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.userPostInteraction.CommentResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { UserMapper.class, UserMapperHelper.class })
public interface CommentMapper {

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(source = "postId", target = "post", qualifiedByName = "postIdToPost")
    Comment toEntity(CommentRequestDTO commentDTO);
    @Mapping(target = "post", ignore = true) // Ignora il campo 'post'
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserResponsePartialDTO")
    @Named("CommentToCommentResponseDTO")
    CommentResponseDTO toResponseDTO(Comment comment);
}

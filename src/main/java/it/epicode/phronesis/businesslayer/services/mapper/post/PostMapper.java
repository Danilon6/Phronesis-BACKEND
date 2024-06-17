package it.epicode.phronesis.businesslayer.services.mapper.post;

import it.epicode.phronesis.businesslayer.services.dto.post.PostPartialResponseDTO;
import it.epicode.phronesis.businesslayer.services.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.businesslayer.services.mapper.userPostInteraction.CommentMapper;
import it.epicode.phronesis.datalayer.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapperHelper.class, CommentMapper.class})
public interface PostMapper {

    //Utilizzo il metodo userIdToUser definito in PostMapperHelper per convertire lo userId contenuto in PostRequestDTO
    //nell'entità user che andrà in Post
    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    Post toEntity(PostRequestDTO postDTO);

    //Utilizzo il metodo userToUserResponsePartialDTO definito in UserMapper per convertire lo user contenuto in Post
    //in uno user parziale. Questo mi consente di ricevere solo i dati necessari che utilizzo quando mostro il post
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserResponsePartialDTO")
    @Mapping(source = "comments", target = "comments", qualifiedByName = "CommentToCommentResponseDTO")
    @Mapping(source = "likes", target = "likes", qualifiedByName = "LikeToResponseDTO")
    @Named("postToResponseDTO")
    PostResponseDTO toResponseDTO(Post post);

    @Named("postToPostPartialResponseDTO")
    PostPartialResponseDTO postToPostPartialResponseDTO(Post post);
}
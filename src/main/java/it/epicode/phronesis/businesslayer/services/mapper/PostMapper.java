package it.epicode.phronesis.businesslayer.services.mapper;

import it.epicode.phronesis.businesslayer.services.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.post.PostResponseDTO;
import it.epicode.phronesis.datalayer.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, PostMapperHelper.class})
public interface PostMapper {

    //Utilizzo il metodo userIdToUser definito in PostMapperHelper per convertire lo userId contenuto in PostRequestDTO
    //nell'entità user che andrà in Post
    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    Post toEntity(PostRequestDTO postDTO);

    //Utilizzo il metodo userIdToUserResponseForPostDTO definito in UserMapper per convertire lo user contenuto in Post
    //in uno user parziale. Questo mi consente di ricevere solo i dati necessari che utilizzo quando mostro il post
    @Mapping(source = "user", target = "user", qualifiedByName = "userIdToUserResponseForPostDTO")
    PostResponseDTO toResponseDTO(Post post);
}
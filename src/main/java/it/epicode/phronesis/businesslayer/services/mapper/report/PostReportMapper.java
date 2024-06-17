package it.epicode.phronesis.businesslayer.services.mapper.report;

import it.epicode.phronesis.businesslayer.services.dto.report.PostReportRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.report.PostReportResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.businesslayer.services.mapper.post.PostMapperHelper;
import it.epicode.phronesis.datalayer.entities.report.PostReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapperHelper.class, PostMapperHelper.class})
public interface PostReportMapper {

    @Mapping(source = "reportedById", target = "reportedBy", qualifiedByName = "userIdToUser")
    @Mapping(source = "reportedPostId", target = "reportedPost", qualifiedByName = "postIdToPost")
    PostReport toEntity(PostReportRequestDTO postReportRequestDTO);

    @Mapping(source = "reportedBy", target = "reportedBy", qualifiedByName = "userToUserResponsePartialDTO")
    @Mapping(source = "reportedPost", target = "reportedPost", qualifiedByName = "postToPostPartialResponseDTO")
    PostReportResponseDTO toResponseDTO(PostReport postReport);
}

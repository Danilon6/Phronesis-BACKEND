package it.epicode.phronesis.businesslayer.services.mapper.report;

import it.epicode.phronesis.businesslayer.services.dto.report.UserReportRequestDTO;
import it.epicode.phronesis.businesslayer.services.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapper;
import it.epicode.phronesis.businesslayer.services.mapper.UserMapperHelper;
import it.epicode.phronesis.datalayer.entities.report.UserReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserMapperHelper.class})
public interface UserReportMapper {

    @Mapping(source = "reportedById", target = "reportedBy", qualifiedByName = "userIdToUser")
    @Mapping(source = "reportedUserId", target = "reportedUser", qualifiedByName = "userIdToUser")
    UserReport toEntity(UserReportRequestDTO userReportRequestDTO);

    @Mapping(source = "reportedBy", target = "reportedBy", qualifiedByName = "userToUserResponsePartialDTO")
    @Mapping(source = "reportedUser", target = "reportedUser", qualifiedByName = "userToUserResponsePartialDTO")
    UserReportResponseDTO toResponseDTO(UserReport userReport);
}

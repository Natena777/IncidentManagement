package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring")
public interface UserRoleMapper {


    public UserRoles toEntity(CreateUserRoleRequestDto createUserRoleRequestDto);

    default UserRoles toEntityDetails(CreateUserRoleRequestDto createUserRoleRequestDto, Integer currentUserId, String mainRole){
        UserRoles entity = toEntity(createUserRoleRequestDto);
        entity.setCreatedBy(currentUserId);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setMainRole(mainRole);
        entity.setStatus("A");
        return entity;
    }



    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    public UserRoleResponseDto toResponse(UserRoles userRoles);


}

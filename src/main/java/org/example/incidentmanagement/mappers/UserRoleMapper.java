package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {


    public UserRoles toEntity(CreateUserRoleRequestDto createUserRoleRequestDto);

    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    public List<UserRoleResponseDto> toResponseList (List<UserRoles> userRoles);

    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    public UserRoleResponseDto toResponse(UserRoles userRoles);


}

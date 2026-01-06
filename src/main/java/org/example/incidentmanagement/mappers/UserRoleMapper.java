package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;
import org.example.incidentmanagement.service.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface UserRoleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "status", constant = "A")
    @Mapping(source = "mainRole", target = "mainRole", qualifiedByName = "booleanToString")
    @Mapping(source = "roleId", target = "roleId")
    public UserRoles toEntity(CreateUserRoleRequestDto createUserRoleRequestDto);
    default UserRoles toEntityDetails(CreateUserRoleRequestDto createUserRoleRequestDto, Integer currentUserId){
        UserRoles entity = toEntity(createUserRoleRequestDto);
        entity.setCreatedBy(currentUserId);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        return entity;
    }



    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "roleId" , target = "roleName", qualifiedByName = "roleIdToRoleName")
    @Mapping(source = "userId", target = "userName", qualifiedByName = "userIdToFullName")
    public UserRoleResponseDto toResponse(UserRoles userRoles);


}

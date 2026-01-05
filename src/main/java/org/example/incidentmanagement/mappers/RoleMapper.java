package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.entity.Role;
import org.mapstruct.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface RoleMapper {

    @Mapping(source = "name", target = "role")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    RoleResponseDto toRoleResponseDto(Role role);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "newRoleName", target = "name")
    @Mapping(source = "newRoleDescription", target = "description")
    @Mapping(source = "newRoleStatus", target = "status")
    void updateFromDto(UpdateRoleRequestDto dto,
                       @MappingTarget Role role);

    
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleDescription", target = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "status", constant = "A")
    Role toEntity(RoleRequestDto roleRequestDto);
    default Role toEntityDetails(RoleRequestDto roleRequestDto, Integer currentUserId){
        Role entity = toEntity(roleRequestDto);
        entity.setCreatedBy(currentUserId);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        return entity;
    }


}

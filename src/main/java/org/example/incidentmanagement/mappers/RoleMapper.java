package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "name", target = "role")
    RoleResponseDto toRoleResponseDto(Role role);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "newRoleName", target = "name")
    @Mapping(source = "newRoleDescription", target = "description")
    @Mapping(source = "newRoleStatus", target = "status")
    void updateFromDto(UpdateRoleRequestDto dto,
                       @MappingTarget Role role);

    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "roleDescription", target = "description")
    Role toEntity(RoleRequestDto roleRequestDto);


}

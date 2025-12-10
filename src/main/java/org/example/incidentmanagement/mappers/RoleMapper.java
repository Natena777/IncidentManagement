package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.RoleRequestDto;
import org.example.incidentmanagement.dto.RoleResponseDto;
import org.example.incidentmanagement.entity.Role;

public class RoleMapper {

    public static RoleResponseDto toResponse(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setRole(role.getName());
        roleResponseDto.setDescription(role.getDescription());
        roleResponseDto.setStatus(role.getStatus());
        roleResponseDto.setCreatedBy(role.getCreatedBy());
        roleResponseDto.setCreatedon(role.getCreatedOn());
        return roleResponseDto;

    }


    public static Role toEntity(RoleRequestDto roleRequestDto) {
        Role role = new Role();
        role.setName(roleRequestDto.getRoleName());
        role.setDescription(roleRequestDto.getRoleDescription());
        return role;
    }

}

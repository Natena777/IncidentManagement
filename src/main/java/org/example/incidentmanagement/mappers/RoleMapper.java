package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
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

    public static Role updateToEntity(UpdateRoleRequestDto updateRoleRequestDto) {
        Role role = new Role();
        role.setName(updateRoleRequestDto.getNewRoleName());
        role.setDescription(updateRoleRequestDto.getNewRoleDescription());
        role.setStatus(updateRoleRequestDto.getNewRoleStatus());
        return role;
    }

}

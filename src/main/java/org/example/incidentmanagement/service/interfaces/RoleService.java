package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.entity.Role;

import java.util.List;

public interface RoleService {

    public Boolean existsRole(int id);
    public RoleResponseDto findByRoleName(String roleName);
    public RoleResponseDto createRole(RoleRequestDto role);
    public RoleResponseDto updateRole(String name, UpdateRoleRequestDto role);
    public void deleteRole(String name);
    public List<Role> findAll();


}

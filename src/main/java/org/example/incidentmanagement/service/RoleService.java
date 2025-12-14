package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.RoleRequestDto;
import org.example.incidentmanagement.dto.response.RoleResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateRoleRequestDto;
import org.example.incidentmanagement.entity.Role;

import java.util.List;

public interface RoleService {

    public RoleResponseDto findByRoleName(String roleName);
    public RoleResponseDto create(RoleRequestDto role);
    public RoleResponseDto update(String name, UpdateRoleRequestDto role);
    public void delete(String name);
    public List<Role> findAll();


}

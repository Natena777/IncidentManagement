package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.RoleRequestDto;
import org.example.incidentmanagement.dto.RoleResponseDto;
import org.example.incidentmanagement.entity.Role;

import java.util.List;

public interface RoleService {

    public Role findByRoleName(String roleName);
    public RoleResponseDto create(RoleRequestDto role);
    public Role update(Role role);
    public void delete(String name);
    public List<Role> findAll();


}

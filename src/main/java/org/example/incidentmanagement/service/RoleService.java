package org.example.incidentmanagement.service;

import org.example.incidentmanagement.entity.Role;

import java.util.List;

public interface RoleService {

    public Role findByRoleName(String roleName);
    public Role create(Role role);
    public Role update(Role role);
    public void delete(String name);
    public List<Role> findAll();


}

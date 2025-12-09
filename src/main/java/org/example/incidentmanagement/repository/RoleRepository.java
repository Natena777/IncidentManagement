package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.Role;

import java.util.List;

public interface RoleRepository {

    public Role findByName(String name);
    public List<Role> findAll();
    public void create(Role role);
    public void update(int id, Role role);
    public void delete(Role role);

}

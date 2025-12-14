package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;

import java.util.List;

public interface UserRolesRepository {


    public List<UserRoles> findAll();
    public UserRoles findById(int id);
    public List<UserRoles> findByUserId(int userId);
    public List<UserRoles> findByRoleId(int roleID);
    public void save(UserRoles userRoles);
    public void delete(UserRoles userRoles);
    public void update(UserRoles userRoles);

}
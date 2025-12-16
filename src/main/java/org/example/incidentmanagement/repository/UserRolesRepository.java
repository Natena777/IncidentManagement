package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    public Boolean existsByUserIdAndRoleId(int userId, int roleId);
    public List<UserRoles> findAll();
    public UserRoles findById(int id);
    public List<UserRoles> findByUserId(int userId);
    public List<UserRoles> findByRoleId(int roleID);

}
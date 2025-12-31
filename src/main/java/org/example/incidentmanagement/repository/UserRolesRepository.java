package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    public Boolean existsByUserIdAndRoleId(int userId, int roleId);
    public UserRoles findById(int id);
    public UserRoles findByUserId(int userId);
    public List<UserRoles> findByRoleId(int roleID);
    



    @Query("select ur.id from UserRoles ur where ur.userId = :userID and ur.roleId = :roleID" )
    public Integer findUserRoleId(Integer userID, Integer roleID);


}
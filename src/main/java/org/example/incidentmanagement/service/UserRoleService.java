package org.example.incidentmanagement.service;


import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {

    Boolean existsUserRole(int userId, int roleId);
    UserRoleResponseDto findUserRoleById(int id);
    List<UserRoleResponseDto> findUserRolesByUserID(int userID);
    List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID);
    UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto);
    void deleteUserRole(int id);
    List<UserRoleResponseDto> findAllUserRoles();
    Boolean existUserRole(int id, String type);
    Integer findUserRoleId(Integer userID, Integer roleID);


}

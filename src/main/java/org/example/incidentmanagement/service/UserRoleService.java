package org.example.incidentmanagement.service;


import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {

    public Boolean existsUserRole(int userId, int roleId);
    public UserRoleResponseDto findUserRoleById(int id);
    public UserRoleResponseDto findUserRolesByUserID(int userID);
    public List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID);
    public UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto);
    public void deleteUserRole(int id);
    public List<UserRoleResponseDto> findAllUserRoles();



}

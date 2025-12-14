package org.example.incidentmanagement.service;


import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRoleService {


    public UserRoleResponseDto findUserRoleById(int id);
    public List<UserRoleResponseDto> findUserRolesByUserID(int userID);
    public List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID);
    public UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto);
    public UserRoleResponseDto updateUserRole(UpdateUserRoleRequestDto updateUserRoleRequestDto);
    public UserRoleResponseDto deleteUserRole(int id);
    public List<UserRoleResponseDto> findAllUserRoles();



}

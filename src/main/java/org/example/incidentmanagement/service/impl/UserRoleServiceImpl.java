package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.mappers.UserRoleMapper;
import org.example.incidentmanagement.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public UserRoleResponseDto findUserRoleById(int id) {
        return null;
    }

    @Override
    public List<UserRoleResponseDto> findUserRolesByUserID(int userID) {
        return null;
    }

    @Override
    public List<UserRoleResponseDto> findUsersRoleByRoleId(int roleID) {
        return null;
    }

    @Override
    public UserRoleResponseDto createUserRole(CreateUserRoleRequestDto createUserRoleRequestDto) {
        return null;
    }

    @Override
    public UserRoleResponseDto updateUserRole(UpdateUserRoleRequestDto updateUserRoleRequestDto) {
        return null;
    }

    @Override
    public UserRoleResponseDto deleteUserRole(int id) {
        return null;
    }

    @Override
    public List<UserRoleResponseDto> findAllUserRoles() {
        return List.of();
    }
}

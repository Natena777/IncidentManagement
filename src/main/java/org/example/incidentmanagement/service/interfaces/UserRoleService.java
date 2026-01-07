package org.example.incidentmanagement.service.interfaces;


import org.example.incidentmanagement.dto.createRequest.CrUserRoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {

    UserRoleResponseDto findUserRoleById(int id);
    UserRoleResponseDto findUserRolesByUserID(int userID);
    List<UserRoleResponseDto> findUsersInRoleById(int roleID);
    UserRoleResponseDto createUserRole(CrUserRoleRequestDto crUserRoleRequestDto);
    UserRoleResponseDto updateUserRole(Integer id, UpdateUserRoleRequestDto updateUserRoleRequestDto);
    void deleteUserRole(int id);
    List<UserRoleResponseDto> findAllUserRoles();
    Boolean existUserRole(int id, String type);
    Integer findUserRoleId(Integer userID, Integer roleID);
    List<UserRoleResponseDto> findUserAllRoles(int userId);

}

package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateUserRoleRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateUserRoleRequestDto;
import org.example.incidentmanagement.dto.response.UserRoleResponseDto;
import org.example.incidentmanagement.entity.UserRoles;

public class UserRoleMapper {


    public UserRoles toEntity(CreateUserRoleRequestDto createUserRoleRequestDto) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(createUserRoleRequestDto.getUserID());
        userRoles.setRoleId(createUserRoleRequestDto.getRoleID());
        userRoles.setMainRole(createUserRoleRequestDto.getMainRole());
        return userRoles;
    }


    public UserRoleResponseDto toResponseDto(UserRoles userRoles) {
        UserRoleResponseDto userRoleResponseDto = new UserRoleResponseDto();
        userRoleResponseDto.setUserId(userRoles.getUserId());
        userRoleResponseDto.setRoleID(userRoles.getRoleId());
        userRoleResponseDto.setMainRole(userRoles.getMainRole());
        return userRoleResponseDto;
    }


    public UserRoles updateToEntity(UpdateUserRoleRequestDto updateUserRoleRequestDto) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(updateUserRoleRequestDto.getUserId());
        userRoles.setRoleId(updateUserRoleRequestDto.getRoleId());
        return userRoles;
    }



}

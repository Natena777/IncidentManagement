package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.requests.UpdateUserReqDto;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto findUserByUsername(String username);

    UserResponseDto findUserByEmail(String email);

    UserResponseDto findUserById(int id);

    List<UserResponseDto> findAllUsers();

    void deleteUser(int id);

    UserResponseDto updateUser(Integer id, UpdateUserReqDto updateUserReqDto);

    Boolean existsUser(int id);


}

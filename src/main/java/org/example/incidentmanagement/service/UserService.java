package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto findUserByUsername(String username);

    UserResponseDto findUserByEmail(String email);

    UserResponseDto findUserById(int id);

    List<UserResponseDto> findAllUsers();

    void deleteUser(int id);

    void updateUser(User user);

    Boolean existsUser(int id);


}

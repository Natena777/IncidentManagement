package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;


public class UserMapper {


    public static UserResponseDto toResponse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstname());
        userResponseDto.setLastName(user.getLastname());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}

package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(RegistrationUserDto dto);

        default User toEntityDetails(RegistrationUserDto regDto, String userName, String fullName, String password) {
            User entity = toEntity(regDto);
            entity.setUsername(userName);
            entity.setFullName(fullName);
            entity.setPassword(password);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setStartDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setActive("A");

        } 

    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toResponseDtoList(List<User> users);

    RegistrationUserRespDto toRegistrationResp(User user);

}
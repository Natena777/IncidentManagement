package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.RegistrationUserDto;
import org.example.incidentmanagement.dto.response.RegistrationUserRespDto;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "active", constant = "A")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(RegistrationUserDto dto);
        default User toEntityDetails(RegistrationUserDto regDto, String userName, String fullName, String password) {
            User entity = toEntity(regDto);
            entity.setUsername(userName);
            entity.setFullName(fullName);
            entity.setPassword(password);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setStartDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;

        } 

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toResponseDtoList(List<User> users);

    RegistrationUserRespDto toRegistrationResp(User user);

}

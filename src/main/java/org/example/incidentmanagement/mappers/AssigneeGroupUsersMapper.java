package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.service.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateAssigneeGroupUsersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface AssigneeGroupUsersMapper {


    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToFullName")
    @Mapping(source = "assigneeGroupId", target = "assigneeGroup", qualifiedByName = "assigneeGroupIdToGroupName")
    CreateAssigneeGroupUsersResponseDto toResponseCrGroupUsers(AssigneeGroupUsers assigneeGroupUsers);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    AssigneeGroupUsers toEntity(CreateAssigneeGroupUsersRequestDto requestDto);
        default AssigneeGroupUsers toAssigneeGroupUsersEntityDetails(CreateAssigneeGroupUsersRequestDto requestDto, Integer currentUserId) {
            AssigneeGroupUsers entity = toEntity(requestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToFullName")
    @Mapping(source = "assigneeGroupId", target = "assigneeGroup", qualifiedByName = "assigneeGroupIdToGroupName")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    AssigneeGroupUsersResponseDto toResponseGroupUsers(AssigneeGroupUsers assigneeGroupUsers);
        
}

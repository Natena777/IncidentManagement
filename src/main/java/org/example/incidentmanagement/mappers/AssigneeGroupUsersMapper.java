package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface AssigneeGroupUsersMapper {

    CreateAssigneeGroupUsersResponseDto toCreateAssigneeGroupUsersResponseDto(AssigneeGroupUsers assigneeGroupUsers);
        default CreateAssigneeGroupUsersResponseDto toResponseCrGroupUsers(AssigneeGroupUsers assigneeGroupUsers, String fullName, String assigneeGroup){
            CreateAssigneeGroupUsersResponseDto response = toCreateAssigneeGroupUsersResponseDto(assigneeGroupUsers);
            response.setUser(fullName);
            response.setAssigneeGroup(assigneeGroup);
            return response;
        }

    AssigneeGroupUsers toAssigneeGroupUsersEntity(CreateAssigneeGroupUsersRequestDto requestDto);

        default AssigneeGroupUsers toAssigneeGroupUsersEntityDetails(CreateAssigneeGroupUsersRequestDto requestDto, Integer currentUserId) {
            AssigneeGroupUsers entity = toAssigneeGroupUsersEntity(requestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setActive("A");
            return entity;
        }

    AssigneeGroupUsersResponseDto toAssigneeGroupUsersResponse(AssigneeGroupUsers assigneeGroupUsers);
        default AssigneeGroupUsersResponseDto toResponseGroupUsers(AssigneeGroupUsers assigneeGroupUsers, String fullName, String assigneeGroup, String createdBy, String updatedBy){
            AssigneeGroupUsersResponseDto response = toAssigneeGroupUsersResponse(assigneeGroupUsers);
            response.setCreatedBy(fullName);
            response.setAssigneeGroup(assigneeGroup);
            response.setCreatedBy(createdBy);
            response.setUpdatedBy(updatedBy);
            return response;
        }



}

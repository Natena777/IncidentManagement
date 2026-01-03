package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper
public interface AssigneeGroupUsersMapper {

    CreateAssigneeGroupUsersResponseDto toCreateAssigneeGroupUsersResponseDto(AssigneeGroupUsers assigneeGroupUsers);

    AssigneeGroupUsers toAssigneeGroupUsersEntity(CreateAssigneeGroupUsersRequestDto requestDto);

        default AssigneeGroupUsers toAssigneeGroupUsersEntityDetails(CreateAssigneeGroupUsersRequestDto requestDto, Integer currentUserId) {
            AssigneeGroupUsers entity = toAssigneeGroupUsersEntity(requestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setActive("A");
            return entity;
        }

    AssigneeGroupUsersResponseDto toAssigneeGroupUsersResponse(AssigneeGroupUsers assigneeGroupUsers);







}

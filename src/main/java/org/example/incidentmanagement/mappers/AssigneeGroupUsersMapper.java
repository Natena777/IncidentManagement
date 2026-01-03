package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.entity.AssigneeGroupUsers;

@Mapper
public interface AssigneeGroupUsersMapper {

    CreateAssigneeGroupUsersResponseDto toCreateAssigneeGroupUsersResponseDto(AssigneeGroupUsers assigneeGroupUsers);

    AssigneeGroupUsers toAssigneeGroupUsersEntity(CreateAssigneeGroupUsersRequestDto requestDto);

        default AssigneeGroupUsers toAssigneeGroupUsersEntityDetails(CreateAssigneeGroupUsersRequestDto requestDto, Integer currentUserId) {
            AssigneeGroupUsers entity = toAssigneeGroupUsersEntity(requestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setActive('A');
            return entity;
        }

    AssigneeGroupUsersResponse toAssigneeGroupUsersResponse(AssigneeGroupUsers assigneeGroupUsers);







}

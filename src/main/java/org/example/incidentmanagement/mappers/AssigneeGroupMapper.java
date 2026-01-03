package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface AssigneeGroupMapper {

    // Response Mapping For API Endpoints
    AssigneeGroupResponseDto toGroupResponseDto(AssigneeGroups assigneeGroups);

    default AssigneeGroupResponseDto toGroupResponseDtoWithUserNames(AssigneeGroups assigneeGroups,
                                                                    String createdByName,
                                                                    String updatedByName) {
        AssigneeGroupResponseDto dto = toGroupResponseDto(assigneeGroups);
        dto.setCreatedBy(createdByName);
        dto.setUpdatedBy(updatedByName);
        return dto;
    }


    
    // Request Mapping For Creating New Assignee Group
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", ignore = true)
    AssigneeGroups toEntity(CreateAssigneeGroupRequestDto dto);

    // Request Mapping For Creating New Assignee Group with Default Values
    default AssigneeGroups toEntityWithDefaults(CreateAssigneeGroupRequestDto dto,
                                                Integer currentUserId) {
        AssigneeGroups entity = toEntity(dto);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setActive("A");
        entity.setCreatedBy(currentUserId);
        return entity;
    }
}
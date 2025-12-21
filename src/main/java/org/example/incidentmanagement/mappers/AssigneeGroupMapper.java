package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssigneeGroupMapper {


    AssigneeGroupResponseDto toGroupResponseDto(AssigneeGroups assigneeGroups);

    AssigneeGroups toEntity (CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto);

}

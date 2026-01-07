package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateAssigneeGroupReqDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssigneeGroupMapper {

    // Response Mapping For API Endpoints
    @Mapping(target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(target = "updatedBy", qualifiedByName = "userIdToFullName")
    AssigneeGroupResponseDto toGroupResponseDto(AssigneeGroups assigneeGroups);
 
    // Request Mapping For Creating New Assignee Group
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "Y")
    AssigneeGroups toGroupEntity(CrAssigneeGroupRequestDto dto);
    default AssigneeGroups toEntityWithDefaults(CrAssigneeGroupRequestDto dto,
                                                Integer currentUserId) {
        AssigneeGroups entity = toGroupEntity(dto);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(source = "active", target = "active", qualifiedByName = "booleanToString")
    void toUpdateEntity(UpdateAssigneeGroupReqDto updateAssigneeGroupReqDto, @MappingTarget AssigneeGroups entity);


}
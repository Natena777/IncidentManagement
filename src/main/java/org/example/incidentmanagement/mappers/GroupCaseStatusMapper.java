package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.requests.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.response.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface GroupCaseStatusMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    AssigneeGroupCaseStatus toGroupCaseStatusEntity(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto);
        default AssigneeGroupCaseStatus toGroupCaseStatusEntityDefaults (CrGroupCaseStatRequestDto crGroupCaseStatRequestDto, Integer currentUserId){
            AssigneeGroupCaseStatus entity = toGroupCaseStatusEntity(crGroupCaseStatRequestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "assigneeGroupId", target = "assigneeGroup", qualifiedByName = "assigneeGroupIdToGroupName")
    @Mapping(source = "currentStatusId", target = "currentStatus", qualifiedByName = "caseStatusIdToStatusName")
    @Mapping(source = "previousStatusId", target = "previousStatus", qualifiedByName = "caseStatusIdToStatusName")
    @Mapping(source = "nextStatusId", target = "nextStatus", qualifiedByName = "caseStatusIdToStatusName")
    CrGroupCaseStatResponseDto toCaseStatusResponse(AssigneeGroupCaseStatus assigneeGroupCaseStatus);

}

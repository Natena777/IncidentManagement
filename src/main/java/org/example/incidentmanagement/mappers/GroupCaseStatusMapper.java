package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateGroupCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.GroupCaseStatRespDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring",
        uses = {DefaultConverter.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GroupCaseStatusMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "Y")
    @Mapping(target = "currentStatusId", ignore = true)
    @Mapping(target = "previousStatusId", ignore = true)
    @Mapping(target = "nextStatusId", ignore = true)
    @Mapping(target = "assigneeGroupId", ignore = true)
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
    GroupCaseStatRespDto toCaseStatusResponse(AssigneeGroupCaseStatus assigneeGroupCaseStatus);

    @Mapping(source = "active", target = "active", qualifiedByName = "booleanToString")
    void toUpdateGroupCaseStatus (UpdateGroupCaseStatusReqDto dto, @MappingTarget AssigneeGroupCaseStatus entity);


}

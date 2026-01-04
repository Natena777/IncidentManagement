package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.requests.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.response.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface GroupCaseStatusMapper {


    AssigneeGroupCaseStatus toGroupCaseStatusEntity(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto);
        default AssigneeGroupCaseStatus toGroupCaseStatusEntityDefaults (CrGroupCaseStatRequestDto crGroupCaseStatRequestDto, Integer currentUserId){
            AssigneeGroupCaseStatus entity = toGroupCaseStatusEntity(crGroupCaseStatRequestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

    CrGroupCaseStatResponseDto toCaseStatusResponse(AssigneeGroupCaseStatus assigneeGroupCaseStatus);
        default CrGroupCaseStatResponseDto toCaseStatusResponse (AssigneeGroupCaseStatus assigneeGroupCaseStatus, String curStatus,
                                                                 String prevStatus, String nextStatus, String assigneeGroup,
                                                                 String createdBy, String updatedBy) {
            CrGroupCaseStatResponseDto responseDto = toCaseStatusResponse(assigneeGroupCaseStatus);
            responseDto.setCurrentStatus(curStatus);
            responseDto.setPreviousStatus(prevStatus);
            responseDto.setNextStatus(nextStatus);
            responseDto.setAssigneeGroup(assigneeGroup);
            responseDto.setCreatedBy(createdBy);
            responseDto.setUpdatedBy(updatedBy);
            return responseDto;
        }



}

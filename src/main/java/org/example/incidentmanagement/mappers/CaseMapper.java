package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.entity.Cases;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface CaseMapper {



    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "caseStatus", target = "caseStatus", qualifiedByName = "caseStatusIdToStatusName")
    @Mapping(source = "scDepartmentId", target = "scDepartmentName", qualifiedByName = "ScDepartmentIdToName")
    @Mapping(source = "scCategoryId", target = "scCategoryName", qualifiedByName = "ScCategoryIdToName")
    @Mapping(source = "scSubCategoryId", target = "scSubCategoryName", qualifiedByName = "ScSubCategoryIdToName")
    @Mapping(source = "scServiceId", target = "scServiceName", qualifiedByName = "ScServiceIdToName")
    @Mapping(source = "assigneeGroupId", target = "assigneeGroupName", qualifiedByName = "assigneeGroupIdToGroupName")
    CreateCaseResponseDto toCreateCaseResponse(Cases cases);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "caseStatus", constant = "1")
    @Mapping(target = "channel", constant = "Portal")
    Cases toEntity(CreateCaseRequestDto createCaseRequestDto);
        default Cases toCaseEntity(CreateCaseRequestDto createCaseRequestDto, Integer currentUser,
                                   Integer assigneeGroup) {
            Cases entity = toEntity(createCaseRequestDto);
            entity.setAssigneeGroupId(assigneeGroup);
            entity.setCreatedBy(currentUser);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

}

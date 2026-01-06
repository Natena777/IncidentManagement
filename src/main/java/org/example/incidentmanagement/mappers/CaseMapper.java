package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.entity.Cases;
import org.example.incidentmanagement.service.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface CaseMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "createdOn", target = "createdOn", ignore = true)
    @Mapping(source = "caseStatus", target = "caseStatus", qualifiedByName = "caseStatusIdToStatusName")
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "channel", ignore = true)
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
    @Mapping(target = "caseStatus", ignore = true)
    @Mapping(target = "channel", ignore = true)
    @Mapping(target = "scDepartmentId", ignore = true)
    @Mapping(target = "scCategoryId", ignore = true)
    @Mapping(target = "scSubCategoryId", ignore = true)
    @Mapping(target = "scServiceId", ignore = true)
    @Mapping(target = "assigneeGroupId", ignore = true)
    @Mapping(target = "assigneeUserId", ignore = true)
    Cases toEntity(CreateCaseRequestDto createCaseRequestDto);
        default Cases toCaseEntity(CreateCaseRequestDto createCaseRequestDto, Integer currentUser) {
            Cases entity = toEntity(createCaseRequestDto);
            entity.setCreatedBy(currentUser);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

}

package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CrScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateScDepartmentsReqDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.entity.ScDepartments;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScDepartmentsMapper {


    //Return Departments For Create Assigne Group
    CrScDepartmentsResponseDto toCreateScDepartmentsResponseDto (ScDepartments scDepartments);

    //Return Departments For All Other Response
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    ScDepartmentsResponseDto toResponseScDepartmentsDto (ScDepartments scDepartments);

    //Create Assignee Group to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "Y")
    ScDepartments toScDepartmentsEntity (CrScDepartmentsRequestDto crScDepartmentsRequestDto);
        default ScDepartments toScDepartmentsEntityDefaults(CrScDepartmentsRequestDto crScDepartmentsRequestDto, Integer currentUserId){
            ScDepartments entity = toScDepartmentsEntity(crScDepartmentsRequestDto);
            entity.setCreatedDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setCreatedBy(currentUserId);
            return entity;
        }


    @Mapping(source = "active", target = "active", qualifiedByName = "booleanToString")
    void toUpdateScDepartmentsEntity(UpdateScDepartmentsReqDto dto, @MappingTarget ScDepartments scDepartments);

}


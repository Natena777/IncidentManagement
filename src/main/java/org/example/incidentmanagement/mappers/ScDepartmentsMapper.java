package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.entity.ScDepartments;
import org.example.incidentmanagement.service.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface ScDepartmentsMapper {

    //Return List of Departments For Response
    List<ScDepartmentsResponseDto> toResponseScDepartmentsList (List<ScDepartments> scDepartments);

    //Return Departments For Create Assigne Group
    
    CreateScDepartmentsResponseDto toCreateScDepartmentsResponseDto (ScDepartments scDepartments);

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
    @Mapping(target = "active", constant = "A")
    ScDepartments toScDepartmentsEntity (CreateScDepartmentsRequestDto createScDepartmentsRequestDto);
    default ScDepartments toScDepartmentsEntityDefaults(CreateScDepartmentsRequestDto createScDepartmentsRequestDto, Integer currentUserId){
        ScDepartments entity = toScDepartmentsEntity(createScDepartmentsRequestDto);
        entity.setCreatedDate(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setCreatedBy(currentUserId);
        return entity;
    }

}

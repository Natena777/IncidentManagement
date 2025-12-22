package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.response.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.entity.ScDepartments;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScDepartmentsMapper {

    //Return List of Departments For Response
    List<ScDepartmentsResponseDto> toResponseScDepartmentsList (List<ScDepartments> scDepartments);

    //Return Departments For Create Assigne Group
    CreateScDepartmentsResponseDto toCreateScDepartmentsResponseDto (ScDepartments scDepartments);

    //Return Departments For All Other Response
    ScDepartmentsResponseDto toResponseScDepartmentsDto (ScDepartments scDepartments);

    //Create Assignee Group to Entity
    ScDepartments toScDepartmentsEntity (CreateScDepartmentsRequestDto createScDepartmentsRequestDto);

}
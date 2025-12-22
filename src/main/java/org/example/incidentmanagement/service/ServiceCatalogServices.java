package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.response.CreateScDepartmentsResponseDto;import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;

import java.util.List;

public interface ServiceCatalogServices {

    List<ScDepartmentsResponseDto> findAllScDepartments();
    ScDepartmentsResponseDto findScDepartmentById(Integer id);
    ScDepartmentsResponseDto findScDepartmentByName(String departmentName);
    CreateScDepartmentsResponseDto createScDepartments(CreateScDepartmentsRequestDto createScDepartmentsRequestDto);
    void deleteScDepartments(Integer id);
}

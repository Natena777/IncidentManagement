package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.*;
import org.example.incidentmanagement.dto.response.*;

import java.util.List;
import java.util.Optional;

public interface ServiceCatalogServices {

    //Sc Departments Services
    List<ScDepartmentsResponseDto> findAllScDepartments();
    ScDepartmentsResponseDto findScDepartmentById(Integer id);
    ScDepartmentsResponseDto findScDepartmentByName(String departmentName);
    String getScDepartmentName(Integer id);
    CreateScDepartmentsResponseDto createScDepartments(CreateScDepartmentsRequestDto createScDepartmentsRequestDto);
    void deleteScDepartments(Integer id);


    //Sc Category Services
    List<ScCategoryResponseDto> findAllScCategories();
    ScCategoryResponseDto findScCategoryById(Integer id);
    ScCategoryResponseDto findScCategoryByName(String categoryName);
    CreateScCategoryResponseDto createScCategory(CreateScCategoryRequestDto createScCategoryRequestDto);
    void deleteScCategory(Integer id);

}

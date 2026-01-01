package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.*;
import org.example.incidentmanagement.dto.response.*;

import java.util.List;

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
    String getScCategoryName(Integer id);
    void deleteScCategory(Integer id);


    //Sc SubCategory Services
    List<ScSubCategoryResponseDto> findAllScSubCategories();
    ScSubCategoryResponseDto findScSubCategoryById(Integer id);
    ScSubCategoryResponseDto findScSubCategoryByName(String subCategoryName);
    CreateScSubCategoryResponseDto createScSubCategory(CreateScSubCategoryRequestDto createScSubCategoryRequestDto);
    String getScSubCategoryName(Integer id);
    void deleteScSubCategory(Integer id);


    //Sc Services service
    List<ScServicesResponseDto> findAllScServices();
    ScServicesResponseDto findScServicesById(Integer id);
    ScServicesResponseDto findScServicesByName(String serviceName);
    CreateScServicesResponseDto createScServices(CreateScServicesRequestDto createScServicesRequestDto);
    List<ServiceCatalogFullResponseDto> getScFullFlow();
    void deleteScServices(Integer id);

}

package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.createRequest.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScServicesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.*;

import java.util.List;

public interface ServiceCatalogServices {

    //Sc Departments Services
    List<ScDepartmentsResponseDto> findAllScDepartments();
    ScDepartmentsResponseDto findScDepartmentById(Integer id);
    ScDepartmentsResponseDto findScDepartmentByName(String departmentName);
    CreateScDepartmentsResponseDto createScDepartments(CreateScDepartmentsRequestDto createScDepartmentsRequestDto);
    void deleteScDepartments(Integer id);


    //Sc Category Services
    List<ScCategoryResponseDto> findAllScCategories();
    ScCategoryResponseDto findScCategoryById(Integer id);
    ScCategoryResponseDto findScCategoryByName(String categoryName);
    CreateScCategoryResponseDto createScCategory(CreateScCategoryRequestDto createScCategoryRequestDto);
    void deleteScCategory(Integer id);


    //Sc SubCategory Services
    List<ScSubCategoryResponseDto> findAllScSubCategories();
    ScSubCategoryResponseDto findScSubCategoryById(Integer id);
    ScSubCategoryResponseDto findScSubCategoryByName(String subCategoryName);
    CreateScSubCategoryResponseDto createScSubCategory(CreateScSubCategoryRequestDto createScSubCategoryRequestDto);
    void deleteScSubCategory(Integer id);


    //Sc Services service
    List<ScServicesResponseDto> findAllScServices();
    ScServicesResponseDto findScServicesById(Integer id);
    ScServicesResponseDto findScServicesByName(String serviceName);
    CreateScServicesResponseDto createScServices(CreateScServicesRequestDto createScServicesRequestDto);
    List<ServiceCatalogFullResponseDto> getScFullFlow();
    void deleteScServices(Integer id);

}

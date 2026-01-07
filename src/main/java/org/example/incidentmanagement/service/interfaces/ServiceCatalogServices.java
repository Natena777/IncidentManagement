package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrScCategoryRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScCategoryResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScServicesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateScCategoryReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScDepartmentsReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScServiceReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScSubCategoryReqDto;
import org.example.incidentmanagement.dto.response.*;

import java.util.List;

public interface ServiceCatalogServices {

    //Sc Departments Services
    List<ScDepartmentsResponseDto> findAllScDepartments();
    ScDepartmentsResponseDto findScDepartmentById(Integer id);
    ScDepartmentsResponseDto findScDepartmentByName(String departmentName);
    CrScDepartmentsResponseDto createScDepartments(CrScDepartmentsRequestDto crScDepartmentsRequestDto);
    ScDepartmentsResponseDto updateScDepartments(Integer id, UpdateScDepartmentsReqDto updateScDepartmentsRequestDto);
    void deleteScDepartments(Integer id);


    //Sc Category Services
    List<ScCategoryResponseDto> findAllScCategories();
    ScCategoryResponseDto findScCategoryById(Integer id);
    ScCategoryResponseDto findScCategoryByName(String categoryName);
    CrScCategoryResponseDto createScCategory(CrScCategoryRequestDto crScCategoryRequestDto);
    ScCategoryResponseDto updateScCategory(Integer id, UpdateScCategoryReqDto updateScCategoryReqDto);
    void deleteScCategory(Integer id);


    //Sc SubCategory Services
    List<ScSubCategoryResponseDto> findAllScSubCategories();
    ScSubCategoryResponseDto findScSubCategoryById(Integer id);
    ScSubCategoryResponseDto findScSubCategoryByName(String subCategoryName);
    CrScSubCategoryResponseDto createScSubCategory(CrScSubCategoryRequestDto crScSubCategoryRequestDto);
    ScSubCategoryResponseDto updateScSubCategory(Integer id, UpdateScSubCategoryReqDto updateScSubCategoryReqDto);
    void deleteScSubCategory(Integer id);


    //Sc Services service
    List<ScServicesResponseDto> findAllScServices();
    ScServicesResponseDto findScServicesById(Integer id);
    ScServicesResponseDto findScServicesByName(String serviceName);
    CrScServicesResponseDto createScServices(CreateScServicesRequestDto createScServicesRequestDto);
    ScServicesResponseDto updateScServices(Integer id, UpdateScServiceReqDto updateScServiceReqDto);
    List<ServiceCatalogFullResponseDto> getScFullFlow();
    void deleteScServices(Integer id);

}

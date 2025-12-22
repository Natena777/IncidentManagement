package org.example.incidentmanagement.service.impl;


import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.response.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.entity.ScDepartments;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.ScDepartmentsMapper;
import org.example.incidentmanagement.repository.ScDepartmentsRepository;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceCatalogServicesImpl implements ServiceCatalogServices {

    Logger logger = LoggerFactory.getLogger(ServiceCatalogServicesImpl.class);
    private final ScDepartmentsRepository scDepartmentsRepository;
    private final ScDepartmentsMapper scDepartmentsMapper;
    private final UserService userService;


    public ServiceCatalogServicesImpl(ScDepartmentsRepository scDepartmentsRepository,
                                      ScDepartmentsMapper scDepartmentsMapper,
                                      UserService userService) {
        this.scDepartmentsRepository = scDepartmentsRepository;
        this.scDepartmentsMapper = scDepartmentsMapper;
        this.userService = userService;
    }


    @Override
    public List<ScDepartmentsResponseDto> findAllScDepartments() {
        return List.of();
    }

    @Override
    public ScDepartmentsResponseDto findScDepartmentById(Integer id) {
        ScDepartments scDepartments = scDepartmentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS));
        logger.info("Called Find Service Catalog Department By ID: {}, Department Name: {} ", id, scDepartments.getDepartmentName());

        //Result
        ScDepartmentsResponseDto responseResult = scDepartmentsMapper.toResponseScDepartmentsDto(scDepartments);

        //Check Response Details
        if (responseResult != null) {
            String createdBy = userService.getFullName(scDepartments.getCreatedBy());
            String updatedBy = userService.getFullName(scDepartments.getUpdatedBy());
            if (createdBy != null) {
                responseResult.setCreatedBy(createdBy);
            }
            if (updatedBy != null) {
                responseResult.setUpdatedBy(updatedBy);
            }
        }
        return responseResult;
    }

    @Override
    public ScDepartmentsResponseDto findScDepartmentByName(String departmentName) {
        ScDepartments scDepartments = scDepartmentsRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS) );
        logger.info("Called Find Service Catalog Department By Department Name: {} ", departmentName);

        //Result
        ScDepartmentsResponseDto responseResult = scDepartmentsMapper.toResponseScDepartmentsDto(scDepartments);

        //Check Response Details
        if (responseResult != null) {
            String createdBy = userService.getFullName(scDepartments.getCreatedBy());
            String updatedBy = userService.getFullName(scDepartments.getUpdatedBy());
            if (createdBy != null) {
                responseResult.setCreatedBy(createdBy);
            }
            if (updatedBy != null) {
                responseResult.setUpdatedBy(updatedBy);
            }
        }
        return responseResult;
    }

    @Override
    public CreateScDepartmentsResponseDto createScDepartments(CreateScDepartmentsRequestDto createScDepartmentsRequestDto) {

        return null;

    }

    @Override
    public void deleteScDepartments(Integer id) {

    }
}
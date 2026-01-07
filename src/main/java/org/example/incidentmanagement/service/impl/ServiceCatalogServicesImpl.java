package org.example.incidentmanagement.service.impl;


import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CrScCategoryRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScCategoryResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScServicesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.*;
import org.example.incidentmanagement.entity.*;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.ScCategoryMapper;
import org.example.incidentmanagement.mappers.ScDepartmentsMapper;
import org.example.incidentmanagement.mappers.ScServicesMapper;
import org.example.incidentmanagement.mappers.ScSubCategoryMapper;
import org.example.incidentmanagement.repository.ScCategoryRepository;
import org.example.incidentmanagement.repository.ScDepartmentsRepository;
import org.example.incidentmanagement.repository.ScServicesRepository;
import org.example.incidentmanagement.repository.ScSubCategoryRepository;
import org.example.incidentmanagement.service.*;
import org.example.incidentmanagement.service.interfaces.AssigneGroupService;
import org.example.incidentmanagement.service.interfaces.ServiceCatalogServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCatalogServicesImpl implements ServiceCatalogServices {

    Logger logger = LoggerFactory.getLogger(ServiceCatalogServicesImpl.class);
    private CurrentUserService currentUserService;
    //Sc Departments Dependencies
    private final ScDepartmentsRepository scDepartmentsRepository;
    private final ScDepartmentsMapper scDepartmentsMapper;

    //Sc Category Dependencies
    private final ScCategoryRepository scCategoryRepository;
    private final ScCategoryMapper scCategoryMapper;

    //Sc SubCategory Dependencies
    private final ScSubCategoryRepository scSubCategoryRepository;
    private final ScSubCategoryMapper scSubCategoryMapper;

    //Sc Services Dependencies
    private final ScServicesRepository scServicesRepository;
    private final ScServicesMapper scServicesMapper;

    public ServiceCatalogServicesImpl(ScDepartmentsRepository scDepartmentsRepository,
                                      ScDepartmentsMapper scDepartmentsMapper,
                                      DefaultConverter defaultConverter,
                                      ScCategoryRepository scCategoryRepository,
                                      ScCategoryMapper scCategoryMapper,
                                      ScSubCategoryRepository scSubCategoryRepository,
                                      ScSubCategoryMapper scSubCategoryMapper,
                                      ScServicesRepository scServicesRepository,
                                      ScServicesMapper scServicesMapper,
                                      AssigneGroupService assigneeGroupService,
                                      CurrentUserService currentUserService) {
        this.scDepartmentsRepository = scDepartmentsRepository;
        this.scDepartmentsMapper = scDepartmentsMapper;
        this.scCategoryRepository = scCategoryRepository;
        this.scCategoryMapper = scCategoryMapper;
        this.scSubCategoryRepository = scSubCategoryRepository;
        this.scSubCategoryMapper = scSubCategoryMapper;
        this.scServicesRepository = scServicesRepository;
        this.scServicesMapper = scServicesMapper;
        this.currentUserService = currentUserService;
    }






    //Sc Departments Services Implementation
    @Override
    public List<ScDepartmentsResponseDto> findAllScDepartments() {
        logger.info("Called Find All Service Catalog Departments");
        List<ScDepartments> scDepartmentsList = scDepartmentsRepository.findAll();
        List<ScDepartmentsResponseDto> resultList = scDepartmentsList.stream()
        .map(department -> scDepartmentsMapper.toResponseScDepartmentsDto(department)).toList();

        return resultList;
    }


    @Override
    public ScDepartmentsResponseDto findScDepartmentById(Integer id) {
        ScDepartments scDepartments = scDepartmentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS));
        logger.info("Called Find Service Catalog Department By ID: {}, Department Name: {} ", id, scDepartments.getDepartmentName());

        //Result
        ScDepartmentsResponseDto responseResult = scDepartmentsMapper.toResponseScDepartmentsDto(scDepartments);
        return responseResult;
    }

    @Override
    public ScDepartmentsResponseDto findScDepartmentByName(String departmentName) {
        ScDepartments scDepartments = scDepartmentsRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS) );
        logger.info("Called Find Service Catalog Department By Department Name: {} ", departmentName);

        //Result
        ScDepartmentsResponseDto responseResult = scDepartmentsMapper.toResponseScDepartmentsDto(scDepartments);
        return responseResult;
    }

    @Override
    public CrScDepartmentsResponseDto createScDepartments(CrScDepartmentsRequestDto crScDepartmentsRequestDto) {
        logger.info("Called Create Department {}, {} ", crScDepartmentsRequestDto.getDepartmentName(),
                crScDepartmentsRequestDto.getDescription());
        
        //Map and Save
        ScDepartments scDepartments = scDepartmentsMapper.toScDepartmentsEntityDefaults(crScDepartmentsRequestDto, currentUserService.getCurrentUserId());
        scDepartmentsRepository.save(scDepartments);

        CrScDepartmentsResponseDto result = scDepartmentsMapper.toCreateScDepartmentsResponseDto(scDepartments);

        return result;

    }

    @Override
    public void deleteScDepartments(Integer id) {
        logger.info("Called Delete Service Catalog Department By ID: {} ", id);
        ScDepartments scDepartments = scDepartmentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS));
        scDepartmentsRepository.delete(scDepartments);

    }



    //Sc Category Services Implementation
    @Override
    public CrScCategoryResponseDto createScCategory(CrScCategoryRequestDto crScCategoryRequestDto) {
        
        logger.info("Called Create Service Catalog Category {}, {}", crScCategoryRequestDto.getScCategoryName(),
                crScCategoryRequestDto.getDescription());


        ScCategory scCategory = scCategoryMapper.toScCategoryEntityDefaults(crScCategoryRequestDto, currentUserService.getCurrentUserId());
        scCategoryRepository.save(scCategory);


        CrScCategoryResponseDto result = scCategoryMapper.toCreateScCategoryResponseDto(scCategory);
        return result;
    }



    @Override
    public void deleteScCategory(Integer id) {
        logger.info("Called Delete Service Catalog Category By ID: {} ", id);
        ScCategory scCategory = scCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_CATEGORY));
        scCategoryRepository.delete(scCategory);
        
    }


    @Override
    public List<ScCategoryResponseDto> findAllScCategories() {
        logger.info("Called Find All Service Catalog Categories");
        List<ScCategory> scCategoryList = scCategoryRepository.findAll();
        List<ScCategoryResponseDto> scCategoryResponseDtoList = scCategoryList.stream()
                .map(category -> scCategoryMapper.toScCategoryResponseDto(category))
                .toList();
        
        return scCategoryResponseDtoList;
    }


    @Override
    public ScCategoryResponseDto findScCategoryById(Integer id) {
        logger.info("Called Find Service Catalog Category By ID: {} ", id);
        ScCategory scCategory = scCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_CATEGORY));

        ScCategoryResponseDto scCategoryResponseDto = scCategoryMapper.toScCategoryResponseDto(scCategory);
        return scCategoryResponseDto;
    }


    @Override
    public ScCategoryResponseDto findScCategoryByName(String categoryName) {
        logger.info("Called Find Service Catalog Category By Name: {} ", categoryName);
        ScCategory scCategory = scCategoryRepository.findByScCategoryName(categoryName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_CATEGORY));

        ScCategoryResponseDto scCategoryResponseDto = scCategoryMapper.toScCategoryResponseDto(scCategory);
        return scCategoryResponseDto;
    }



    //Service Catalog Sub Category
    @Override
    public List<ScSubCategoryResponseDto> findAllScSubCategories() {
        logger.info("Called Find All Service Catalog SubCategories");
        List<ScSubCategory> scSubCategoryList = scSubCategoryRepository.findAll();
        List<ScSubCategoryResponseDto> scSubCategoryResponseDtoList = scSubCategoryList.stream()
                .map(subcategory -> scSubCategoryMapper.toScSubCategoryResponseDto(subcategory))
                .toList();

        return scSubCategoryResponseDtoList;
    }

    @Override
    public ScSubCategoryResponseDto findScSubCategoryById(Integer id) {
        logger.info("Called Find Service Catalog SubCategory By ID: {} ", id);
        ScSubCategory scSubCategory = scSubCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SUBCATEGORY));

        ScSubCategoryResponseDto scSubCategoryResponseDto = scSubCategoryMapper.toScSubCategoryResponseDto(scSubCategory);
        return scSubCategoryResponseDto;
    }

    @Override
    public ScSubCategoryResponseDto findScSubCategoryByName(String subCategoryName) {
        logger.info("Called Find Service Catalog SubCategory By Name: {} ", subCategoryName);
        ScSubCategory scSubCategory = scSubCategoryRepository.findByScSubCategoryName(subCategoryName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SUBCATEGORY));

        ScSubCategoryResponseDto scSubCategoryResponseDto = scSubCategoryMapper.toScSubCategoryResponseDto(scSubCategory);

        return scSubCategoryResponseDto;
    }

    @Override
    public CrScSubCategoryResponseDto createScSubCategory(CrScSubCategoryRequestDto crScSubCategoryRequestDto) {
        logger.info("Called Create Service Catalog Category {}, {}", crScSubCategoryRequestDto.getScSubCategoryName(),
                crScSubCategoryRequestDto.getDescription());

        //Map And Save
        ScSubCategory scSubCategory = scSubCategoryMapper.toScSubCategoryEntityDefaults(crScSubCategoryRequestDto, currentUserService.getCurrentUserId());
        scSubCategoryRepository.save(scSubCategory);


        CrScSubCategoryResponseDto result = scSubCategoryMapper.toCreateScSubCategoryResponseDto(scSubCategory);
        return result;
    }


    @Override
    public void deleteScSubCategory(Integer id) {
        logger.info("Called Delete Service Catalog SubCategory By ID: {} ", id);
        ScSubCategory scSubCategory = scSubCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SUBCATEGORY));
        scSubCategoryRepository.delete(scSubCategory);
    }


    @Override
    public List<ScServicesResponseDto> findAllScServices() {
        logger.info("Called Find All Service Catalog Services");
        List<ScServices> scServiceslist = scServicesRepository.findAll();
        List<ScServicesResponseDto> scServicesResponseDtoList = scServiceslist.stream()
                .map(scServices -> {
                    ScServicesResponseDto scServicesResponseDto = scServicesMapper.toScServicesResponseDto(scServices);
                    String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
                    String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();

                    scServicesResponseDto.setResponseTime(responseTime);
                    scServicesResponseDto.setResolutionTime(resolutionTime);

                return scServicesResponseDto;})
                .toList();

        return scServicesResponseDtoList;
    }

    @Override
    public ScServicesResponseDto findScServicesById(Integer id) {
        logger.info("Called Find Service Catalog Service By ID: {} ", id);
        ScServices scServices = scServicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));

        ScServicesResponseDto scServicesResponseDto = scServicesMapper.toScServicesResponseDto(scServices);

        if (scServices != null) {
            //get value
            String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
            String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();

            //set
            scServicesResponseDto.setResponseTime(responseTime);
            scServicesResponseDto.setResolutionTime(resolutionTime);
        }
        return scServicesResponseDto;
    }

    @Override
    public ScServicesResponseDto findScServicesByName(String serviceName) {
        logger.info("Called Find Service Catalog Service By Name: {} ", serviceName);
        ScServices scServices = scServicesRepository.findByServicesName(serviceName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));

        ScServicesResponseDto scServicesResponseDto = scServicesMapper.toScServicesResponseDto(scServices);

        if (scServices != null) {
            //get value
            String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
            String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();

            //set
            scServicesResponseDto.setResponseTime(responseTime);
            scServicesResponseDto.setResolutionTime(resolutionTime);
        }
        return scServicesResponseDto;
    }

    @Override
    public CrScServicesResponseDto createScServices(CreateScServicesRequestDto createScServicesRequestDto) {
        logger.info("Called Create Service Catalog Service ");

        //Map And Save
        ScServices scServices = scServicesMapper.toScServicesEntityDefaults(createScServicesRequestDto, currentUserService.getCurrentUserId());
        scServicesRepository.save(scServices);

        String responseTime = scServices.getResponseTimeType().getDisplayValue() + " " +
                scServices.getResponseTimeValue();
        String resolutionTime = scServices.getResolutionTimeType().getDisplayValue() + " " +
                scServices.getResolutionValue();


        CrScServicesResponseDto scServicesResponseDto = scServicesMapper.toCreateScServicesResponseDto(scServices);
        scServicesResponseDto.setResponseTime(responseTime);
        scServicesResponseDto.setResolutionTime(resolutionTime);


        return scServicesResponseDto;
    }

    @Override
    public List<ServiceCatalogFullResponseDto> getScFullFlow() {
        logger.info("Called Get Service Catalog Service By ID: {} ");
        List<ServiceCatalogFullResponseDto> getScFullFlow = scServicesRepository.findFullFlowServiceCatalog();
        return getScFullFlow;
    }

    @Override
    public void deleteScServices(Integer id) {
        logger.info("Called Delete Service Catalog Service By ID: {} ", id);
        ScServices scServices = scServicesRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));
        scServicesRepository.delete(scServices);

    }
}


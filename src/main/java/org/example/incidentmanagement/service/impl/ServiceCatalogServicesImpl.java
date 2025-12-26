package org.example.incidentmanagement.service.impl;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.incidentmanagement.dto.requests.*;
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
import org.example.incidentmanagement.service.AssigneGroupService;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceCatalogServicesImpl implements ServiceCatalogServices {

    Logger logger = LoggerFactory.getLogger(ServiceCatalogServicesImpl.class);
    //Sc Departments Dependencies
    private final ScDepartmentsRepository scDepartmentsRepository;
    private final ScDepartmentsMapper scDepartmentsMapper;
    private final UserService userService;

    //Sc Category Dependencies
    private final ScCategoryRepository scCategoryRepository;
    private final ScCategoryMapper scCategoryMapper;

    //Sc SubCategory Dependencies
    private final ScSubCategoryRepository scSubCategoryRepository;
    private final ScSubCategoryMapper scSubCategoryMapper;

    //Sc Services Dependencies
    private final ScServicesRepository scServicesRepository;
    private final ScServicesMapper scServicesMapper;
    private final AssigneGroupService assigneeGroupService;

    public ServiceCatalogServicesImpl(ScDepartmentsRepository scDepartmentsRepository,
                                      ScDepartmentsMapper scDepartmentsMapper,
                                      UserService userService,
                                      ScCategoryRepository scCategoryRepository,
                                      ScCategoryMapper scCategoryMapper,
                                      ScSubCategoryRepository scSubCategoryRepository,
                                      ScSubCategoryMapper scSubCategoryMapper,
                                      ScServicesRepository scServicesRepository,
                                      ScServicesMapper scServicesMapper,
                                      AssigneGroupService assigneeGroupService) {
        this.scDepartmentsRepository = scDepartmentsRepository;
        this.scDepartmentsMapper = scDepartmentsMapper;
        this.userService = userService;
        this.scCategoryRepository = scCategoryRepository;
        this.scCategoryMapper = scCategoryMapper;
        this.scSubCategoryRepository = scSubCategoryRepository;
        this.scSubCategoryMapper = scSubCategoryMapper;
        this.scServicesRepository = scServicesRepository;
        this.scServicesMapper = scServicesMapper;
        this.assigneeGroupService = assigneeGroupService;
    }






    //Sc Departments Services Implementation
    @Override
    public List<ScDepartmentsResponseDto> findAllScDepartments() {
        logger.info("Called Find All Service Catalog Departments");
        List<ScDepartments> scDepartmentsList = scDepartmentsRepository.findAll();

        List<ScDepartmentsResponseDto> resultList = scDepartmentsList.stream()
        .map(department -> {
            ScDepartmentsResponseDto responseDto = scDepartmentsMapper.toResponseScDepartmentsDto(department);
            String createdBy = userService.getFullName(department.getCreatedBy());
            String updatedBy = userService.getFullName(department.getUpdatedBy());
                if (createdBy != null) {
                    responseDto.setCreatedBy(createdBy);
                }
                if (updatedBy != null) {
                    responseDto.setUpdatedBy(updatedBy);
                }
                return responseDto;
                })
                .toList();

        return resultList;

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
        logger.info("Called Create Department {}, {}, {} ", createScDepartmentsRequestDto.getDepartmentName(),
                createScDepartmentsRequestDto.getDescription(), createScDepartmentsRequestDto.getActive());
        ScDepartments scDepartments = scDepartmentsMapper.toScDepartmentsEntity(createScDepartmentsRequestDto);
        scDepartments.setCreatedBy(11);
        scDepartments.setCreatedDate(LocalDateTime.now());
        scDepartmentsRepository.save(scDepartments);

        CreateScDepartmentsResponseDto result = scDepartmentsMapper.toCreateScDepartmentsResponseDto(scDepartments);

        return result;

    }

    @Override
    public void deleteScDepartments(Integer id) {
        logger.info("Called Delete Service Catalog Department By ID: {} ", id);
        ScDepartments scDepartments = scDepartmentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_DEPARTMENTS));
        scDepartmentsRepository.delete(scDepartments);

    }

    @Override
    public String getScDepartmentName(Integer id){
        String scDepartmentName = scDepartmentsRepository.findByDepartmenNametId(id);
        return scDepartmentName;
    }



    //Sc Category Services Implementation
    @Override
    public CreateScCategoryResponseDto createScCategory(CreateScCategoryRequestDto createScCategoryRequestDto) {
        
        logger.info("Called Create Service Catalog Category {}, {}, {} ", createScCategoryRequestDto.getScCategoryName(),
                createScCategoryRequestDto.getDescription(), createScCategoryRequestDto.getActive());

        ScCategory scCategory = scCategoryMapper.toScCategoryEntity(createScCategoryRequestDto);

        logger.info("AFTER MAPPING: name={}", scCategory.getScCategoryName());

        scCategory.setCreatedBy(11);
        scCategory.setCreatedOn(LocalDateTime.now());
        scCategoryRepository.save(scCategory);


        logger.info("AFTER SAVE: name={}", scCategory.getScCategoryName());
        String scDepartmentName = getScDepartmentName(scCategory.getScDepartmentId());
        CreateScCategoryResponseDto result = scCategoryMapper.toCreateScCategoryResponseDto(scCategory);
        logger.info("AFTER Result: name={}", result.getScCategoryName());

        result.setScDepartment(scDepartmentName);
        return result;
    }

    @Override
    public String getScCategoryName(Integer id) {
        String scCategoryName =  scCategoryRepository.findByScCategoryNameById(id);
        return scCategoryName;
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
                .map(category -> {ScCategoryResponseDto scCategoryResponseDto = scCategoryMapper.toScCategoryResponseDto(category);
                    String createdBy = userService.getFullName(category.getCreatedBy());
                    String updatedBy = userService.getFullName(category.getUpdatedBy());
                    String scDepartmentName = getScDepartmentName(category.getScDepartmentId());
                    if (createdBy != null) {
                        scCategoryResponseDto.setCreatedBy(createdBy);
                    }
                    if (updatedBy != null) {
                        scCategoryResponseDto.setUpdatedBy(updatedBy);
                    }
                    if (scDepartmentName != null) {
                        scCategoryResponseDto.setScDepartment(scDepartmentName);
                    }
                    return scCategoryResponseDto;
                }).toList();
        
        return scCategoryResponseDtoList;
    }


    @Override
    public ScCategoryResponseDto findScCategoryById(Integer id) {
        logger.info("Called Find Service Catalog Category By ID: {} ", id);
        ScCategory scCategory = scCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_CATEGORY));
        ScCategoryResponseDto scCategoryResponseDto = scCategoryMapper.toScCategoryResponseDto(scCategory);
        String createdBy = userService.getFullName(scCategory.getCreatedBy());
        String updatedBy = userService.getFullName(scCategory.getUpdatedBy());
        String scDepartmentName = getScDepartmentName(scCategory.getScDepartmentId());
        if (createdBy != null) {
            scCategoryResponseDto.setCreatedBy(createdBy);
        }
        if (updatedBy != null) {
            scCategoryResponseDto.setUpdatedBy(updatedBy);
        }
        if (scDepartmentName != null) {
            scCategoryResponseDto.setScDepartment(scDepartmentName);
        }
        return scCategoryResponseDto;
    }


    @Override
    public ScCategoryResponseDto findScCategoryByName(String categoryName) {
        logger.info("Called Find Service Catalog Category By Name: {} ", categoryName);
        ScCategory scCategory = scCategoryRepository.findByScCategoryName(categoryName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_CATEGORY));

        ScCategoryResponseDto scCategoryResponseDto = scCategoryMapper.toScCategoryResponseDto(scCategory);
        String createdBy = userService.getFullName(scCategory.getCreatedBy());
        String updatedBy = userService.getFullName(scCategory.getUpdatedBy());
        String scDepartmentName = getScDepartmentName(scCategory.getScDepartmentId());
        if (createdBy != null) {
            scCategoryResponseDto.setCreatedBy(createdBy);
        }
        if (updatedBy != null) {
            scCategoryResponseDto.setUpdatedBy(updatedBy);
        }
        if (scDepartmentName != null) {
            scCategoryResponseDto.setScDepartment(scDepartmentName);
        }
        return scCategoryResponseDto;
    }



    //Service Catalog Sub Category
    @Override
    public List<ScSubCategoryResponseDto> findAllScSubCategories() {
        logger.info("Called Find All Service Catalog SubCategories");
        List<ScSubCategory> scSubCategoryList = scSubCategoryRepository.findAll();
        List<ScSubCategoryResponseDto> scSubCategoryResponseDtoList = scSubCategoryList.stream()
                .map(subcategory -> {ScSubCategoryResponseDto scSubCategoryResponseDto = scSubCategoryMapper.toScSubCategoryResponseDto(subcategory);
                    String createdBy = userService.getFullName(subcategory.getCreatedBy());
                    String updatedBy = userService.getFullName(subcategory.getUpdatedBy());
                    String scCategoryName = getScCategoryName(subcategory.getScCategoryId());
                    if (createdBy != null) {
                        scSubCategoryResponseDto.setCreatedBy(createdBy);
                    }
                    if (updatedBy != null) {
                        scSubCategoryResponseDto.setUpdatedBy(updatedBy);
                    }
                    if (scCategoryName != null) {
                        scSubCategoryResponseDto.setScCategoryName(scCategoryName);
                    }
                    return scSubCategoryResponseDto;
                }).toList();

        return scSubCategoryResponseDtoList;
    }

    @Override
    public ScSubCategoryResponseDto findScSubCategoryById(Integer id) {
        logger.info("Called Find Service Catalog SubCategory By ID: {} ", id);
        ScSubCategory scSubCategory = scSubCategoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SUBCATEGORY));

        ScSubCategoryResponseDto scSubCategoryResponseDto = scSubCategoryMapper.toScSubCategoryResponseDto(scSubCategory);
        String createdBy = userService.getFullName(scSubCategory.getCreatedBy());
        String updatedBy = userService.getFullName(scSubCategory.getUpdatedBy());
        String scCategoryName = getScCategoryName(scSubCategory.getScCategoryId());
        if (createdBy != null) {
            scSubCategoryResponseDto.setCreatedBy(createdBy);
        }
        if (updatedBy != null) {
            scSubCategoryResponseDto.setUpdatedBy(updatedBy);
        }
        if (scCategoryName != null) {
            scSubCategoryResponseDto.setScCategoryName(scCategoryName);
        }
        return scSubCategoryResponseDto;
    }

    @Override
    public ScSubCategoryResponseDto findScSubCategoryByName(String subCategoryName) {
        logger.info("Called Find Service Catalog SubCategory By Name: {} ", subCategoryName);
        ScSubCategory scSubCategory = scSubCategoryRepository.findByScSubCategoryName(subCategoryName)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SUBCATEGORY));

        ScSubCategoryResponseDto scSubCategoryResponseDto = scSubCategoryMapper.toScSubCategoryResponseDto(scSubCategory);
        String createdBy = userService.getFullName(scSubCategory.getCreatedBy());
        String updatedBy = userService.getFullName(scSubCategory.getUpdatedBy());
        String scCategoryName = getScCategoryName(scSubCategory.getScCategoryId());
        if (createdBy != null) {
            scSubCategoryResponseDto.setCreatedBy(createdBy);
        }
        if (updatedBy != null) {
            scSubCategoryResponseDto.setUpdatedBy(updatedBy);
        }
        if (scCategoryName != null) {
            scSubCategoryResponseDto.setScCategoryName(scCategoryName);
        }
        return scSubCategoryResponseDto;
    }

    @Override
    public CreateScSubCategoryResponseDto createScSubCategory(CreateScSubCategoryRequestDto createScSubCategoryRequestDto) {
        logger.info("Called Create Service Catalog Category {}, {}, {} ", createScSubCategoryRequestDto.getScSubCategoryName(),
                createScSubCategoryRequestDto.getDescription(), createScSubCategoryRequestDto.getActive());

        ScSubCategory scSubCategory = scSubCategoryMapper.toScSubCategory(createScSubCategoryRequestDto);
        scSubCategory.setCreatedBy(11);
        scSubCategory.setCreatedOn(LocalDateTime.now());
        scSubCategoryRepository.save(scSubCategory);

        String scCategoryName = getScCategoryName(createScSubCategoryRequestDto.getScCategoryId());

        CreateScSubCategoryResponseDto result = scSubCategoryMapper.toCreateScSubCategoryResponseDto(scSubCategory);
        result.setScCategoryName(scCategoryName);
        return result;
    }

    @Override
    public String getScSubCategoryName(Integer id) {
        String scSubCategoryName = scSubCategoryRepository.findScSubCategoryNameById(id);
        return scSubCategoryName;
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
                    String createdBy = userService.getFullName(scServices.getCreatedBy());
                    String updatedBy = userService.getFullName(scServices.getUpdatedBy());
                    String scDepartmentName = getScDepartmentName(scServices.getScDepartmentId());
                    String scCategoryName = getScCategoryName(scServices.getScCategoryId());
                    String scSubCategoryName = getScSubCategoryName(scServices.getScSubCategoryId());
                    String assigneeGroupName = assigneeGroupService.getAssigneeGroupName(scServices.getAssigneeGroupId());
                    String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
                    String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();


                    scServicesResponseDto.setCreatedBy(createdBy);
                    scServicesResponseDto.setUpdatedBy(updatedBy);
                    scServicesResponseDto.setScDepartmentName(scDepartmentName);
                    scServicesResponseDto.setScCategoryName(scCategoryName);
                    scServicesResponseDto.setScSubCategoryName(scSubCategoryName);
                    scServicesResponseDto.setAssigneeGroupName(assigneeGroupName);
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
            String scDepartmentName = getScDepartmentName(scServices.getScDepartmentId());
            String scCategoryName = getScCategoryName(scServices.getScCategoryId());
            String scSubCategoryName = getScSubCategoryName(scServices.getScSubCategoryId());
            String assigneeGroupName = assigneeGroupService.getAssigneeGroupName(scServices.getAssigneeGroupId());
            String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
            String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();

            //set
            scServicesResponseDto.setServicesName(scServices.getServicesName());
            scServicesResponseDto.setScDepartmentName(scDepartmentName);
            scServicesResponseDto.setScCategoryName(scCategoryName);
            scServicesResponseDto.setScSubCategoryName(scSubCategoryName);
            scServicesResponseDto.setAssigneeGroupName(assigneeGroupName);
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
            String scDepartmentName = getScDepartmentName(scServices.getScDepartmentId());
            String scCategoryName = getScCategoryName(scServices.getScCategoryId());
            String scSubCategoryName = getScSubCategoryName(scServices.getScSubCategoryId());
            String assigneeGroupName = assigneeGroupService.getAssigneeGroupName(scServices.getAssigneeGroupId());
            String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue();
            String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();

            //set
            scServicesResponseDto.setServicesName(scServices.getServicesName());
            scServicesResponseDto.setScDepartmentName(scDepartmentName);
            scServicesResponseDto.setScCategoryName(scCategoryName);
            scServicesResponseDto.setScSubCategoryName(scSubCategoryName);
            scServicesResponseDto.setAssigneeGroupName(assigneeGroupName);
            scServicesResponseDto.setResponseTime(responseTime);
            scServicesResponseDto.setResolutionTime(resolutionTime);
        }
        return scServicesResponseDto;
    }

    @Override
    public CreateScServicesResponseDto createScServices(CreateScServicesRequestDto createScServicesRequestDto) {
        logger.info("Called Create Service Catalog Service ");

        ScServices scServices = scServicesMapper.toScServicesEntity(createScServicesRequestDto);
        scServices.setCreatedBy(11);
        scServices.setCreatedDate(LocalDateTime.now());
        scServicesRepository.save(scServices);

        String scDepartmentName = getScDepartmentName(scServices.getScDepartmentId());
        String scCategoryName = getScCategoryName(scServices.getScCategoryId());
        String scSubCategoryName = getScSubCategoryName(scServices.getScSubCategoryId());
        String assigneeGroupName = assigneeGroupService.getAssigneeGroupName(scServices.getAssigneeGroupId());
        String responseTime = scServices.getResponseTimeType() + " " + scServices.getResponseTimeValue() ;
        String resolutionTime = scServices.getResolutionTimeType() + " " + scServices.getResolutionValue();


        CreateScServicesResponseDto scServicesResponseDto = scServicesMapper.toCreateScServicesResponseDto(scServices);
        scServicesResponseDto.setServicesName(createScServicesRequestDto.getServicesName());
        scServicesResponseDto.setScDepartmentName(scDepartmentName);
        scServicesResponseDto.setScCategoryName(scCategoryName);
        scServicesResponseDto.setScSubCategoryName(scSubCategoryName);
        scServicesResponseDto.setAssigneeGroupName(assigneeGroupName);
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
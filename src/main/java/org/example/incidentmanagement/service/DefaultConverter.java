package org.example.incidentmanagement.service;


import org.example.incidentmanagement.repository.*;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class DefaultConverter {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AssigneeGroupRepository assigneeGroupRepository;
    private CaseStatusesRepository statusesRepository;
    Logger logger = LoggerFactory.getLogger(DefaultConverter.class);
    private ScDepartmentsRepository scDepartmentsRepository;
    private ScCategoryRepository scCategoryRepository;
    private ScSubCategoryRepository scSubCategoryRepository;
    private ScServicesRepository scServicesRepository;

    public DefaultConverter(UserRepository userRepository,
                            RoleRepository roleRepository,
                            AssigneeGroupRepository assigneeGroupRepository,
                            CaseStatusesRepository statusesRepository,
                            ScDepartmentsRepository scDepartmentsRepository,
                            ScCategoryRepository scCategoryRepository,
                            ScSubCategoryRepository scSubCategoryRepository,
                            ScServicesRepository scServicesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.assigneeGroupRepository = assigneeGroupRepository;
        this.statusesRepository = statusesRepository;
        this.scDepartmentsRepository = scDepartmentsRepository;
        this.scCategoryRepository = scCategoryRepository;
        this.scSubCategoryRepository = scSubCategoryRepository;
        this.scServicesRepository = scServicesRepository;
    }

    @Named("userIdToFullName")
    public String getUserFullName(Integer id) {
        String userFullName = userRepository.findFullNameById(id).orElse(null);
        logger.info("Called GetFullName on ID {}  Result: {}", id, userFullName);
        return userFullName;
    }

    @Named("roleIdToRoleName")
    public String getRoleName(Integer id) {
        String roleName = roleRepository.findRoleNameById(id).orElse(null);
        logger.info("Called GetRoleName on ID {}  Result: {}", id, roleName);
        return roleName;
    }

    @Named("assigneeGroupIdToGroupName")
    public String getAssigneeGroupName(Integer id){
        logger.info("Called Get AssigneeGroupName on ID {}", id);
        String groupName = assigneeGroupRepository.findGroupNameById(id).orElse(null);
        return groupName;
    }

    @Named("caseStatusIdToStatusName")
    public String getCaseStatusName(Integer id){
        logger.info("Called GetCaseStatusName on ID {}", id);
        String statusName = statusesRepository.getNameById(id);
        return statusName;
    }

    @Named("setDefaultCreatedOn")
    public LocalDateTime getDefaultTbilisiTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tbilisi"));
    }


    @Named("booleanToString")
    public String booleanToString(boolean b) {
        String resultText;
        if (b) {
            resultText = "Y";
        } else {
            resultText = "N";
        }
        return resultText;
    }

    @Named("BooleanToInteger")
    public Integer booleanToInteger(boolean b) {
        Integer resultInteger;
        if (b) {
            resultInteger = 1;
        } else {
            resultInteger = 0;
        }

        return resultInteger;
    }

    @Named("IntegerToBoolean")
    public boolean IntegerToBoolean(Integer i) {
        boolean resultbool;
        if (i == 1) {
            resultbool = true;
        }
        else {
            resultbool = false;
        }
        return resultbool;
    }



    //Service Catalog Start
    //Service Catalog Department Start
    @Named("ScDepartmentIdToName")
    public String getScDepartmentName(Integer id) {
        String departmentName;
        departmentName = scDepartmentsRepository.findByDepartmenNametId(id);
        return departmentName;
    }

    @Named("ScDepartmentNameToId")
    public Integer getScDepartmentId(String departmentName) {
        Integer departmentId = scDepartmentsRepository.findByDepartmentName(departmentName).get().getId();
        return departmentId;
    }
    //Service Catalog department End


    //Service Catalog Category Start
    @Named("ScCategoryIdToName")
    public String getScCategoryName(Integer id) {
        String categoryName;
        categoryName = scCategoryRepository.findByScCategoryNameById(id);
        return categoryName;
    }

    @Named("ScCategoryNameToId")
    public Integer getScCategoryId(String categoryName) {
        Integer categoryId;
        categoryId = scCategoryRepository.findByScCategoryName(categoryName).get().getId();
        return categoryId;
    }
    //Service Catalog Category End

    //Service Catalog SubCategory Start
    @Named("ScSubCategoryIdToName")
    public String getScSubCategoryName(Integer id) {
        String subCategoryName;
        subCategoryName = scSubCategoryRepository.findScSubCategoryNameById(id);
        return subCategoryName;
    }

    @Named("ScSubCategoryNameToId")
    public Integer getScSubCategoryId(String subCategoryName) {
        Integer subCategoryId;
        subCategoryId = scSubCategoryRepository.findByScSubCategoryName(subCategoryName).get().getId();
        return subCategoryId;
    }
    //Service Catalog SubCategory End

    //Service Catalog Service Start
    @Named("ScServiceIdToName")
    public String getScServiceName(Integer id) {
        String serviceName;
        serviceName = scServicesRepository.findServiceNameById(id);
        return serviceName;
    }

    @Named("ScServiceNameToId")
    public Integer getScServiceId(String serviceName) {
        Integer serviceId;
        serviceId = scServicesRepository.findByServicesName(serviceName).get().getId();
        return serviceId;
    }
    //Service Catalog Service End
    //Service Catalog End






}

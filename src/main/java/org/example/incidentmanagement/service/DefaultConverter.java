package org.example.incidentmanagement.service;


import org.example.incidentmanagement.repository.CaseStatusesRepository;
import org.example.incidentmanagement.repository.RoleRepository;
import org.example.incidentmanagement.repository.UserRepository;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.example.incidentmanagement.repository.AssigneeGroupRepository;
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

    public DefaultConverter(UserRepository userRepository,
                            RoleRepository roleRepository,
                            AssigneeGroupRepository assigneeGroupRepository,
                            CaseStatusesRepository statusesRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.assigneeGroupRepository = assigneeGroupRepository;
        this.statusesRepository = statusesRepository;
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


}

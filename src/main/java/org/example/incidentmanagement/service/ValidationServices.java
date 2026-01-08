package org.example.incidentmanagement.service;


import org.example.incidentmanagement.repository.*;
import org.springframework.stereotype.Service;

@Service
public class ValidationServices {

    private final CaseRepository caseRepository;
    private final AssigneeGroupUsersRepository assigneeGroupUsersRepository;
    private final ScServicesRepository scServicesRepository;
    private final UserRolesRepository userRolesRepository;



    public ValidationServices(CaseRepository caseRepository,
                              AssigneeGroupUsersRepository assigneeGroupUsersRepository,
                              ScServicesRepository scServicesRepository,
                              UserRolesRepository userRolesRepository) {
        this.caseRepository = caseRepository;
        this.assigneeGroupUsersRepository = assigneeGroupUsersRepository;
        this.scServicesRepository = scServicesRepository;
        this.userRolesRepository = userRolesRepository;
    }

    public boolean existUserInAssigneeGroup(Integer assigneeGroupId) {
        boolean exist = assigneeGroupUsersRepository.existsAssigneeGroupUsersByAssigneeGroupId(assigneeGroupId);
        return exist;
    }

    public boolean existsAssigneeGroupUsersByAssigneeUserId (Integer userId, Integer assigneeGroupId) {
        boolean exist = assigneeGroupUsersRepository.existsAssigneeGroupUsersByAssigneeUserId(userId, assigneeGroupId);
        return exist;
    }

    public boolean existsCaseByAssigneeGroupId(Integer AssigneeGroupId) {
        boolean exist = caseRepository.findAssigneeGroupCases(AssigneeGroupId);
        return  exist;
    }

    public boolean existsScServiceByAssigneeGroupId(Integer AssigneeGroupId) {
        boolean exist = scServicesRepository.existsByAssigneeGroupId(AssigneeGroupId);
        return exist;
    }

    public boolean existsUserInRole(Integer roleId) {
        boolean exist = userRolesRepository.existsByRoleId(roleId);
        return exist;
    }



}

package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.service.*;
import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.service.interfaces.AssigneeGroupUserService;
import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.mappers.AssigneeGroupUsersMapper;
import org.example.incidentmanagement.repository.AssigneeGroupUsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AssigneeGroupUserServiceImpl implements AssigneeGroupUserService {

    private final AssigneeGroupUsersRepository assigneeGroupUsersRepository;
    private final Logger logger = LoggerFactory.getLogger(AssigneeGroupUserServiceImpl.class);
    private final AssigneeGroupUsersMapper assigneeGroupUsersMapper;
    private final CurrentUserService currentUserService;
    private final ValidationServices validationServices;

    public AssigneeGroupUserServiceImpl(AssigneeGroupUsersRepository assigneeGroupUsersRepository, AssigneeGroupUsersMapper assigneeGroupUsersMapper,
                                    CurrentUserService currentUserService, ValidationServices validationServices) {
        this.assigneeGroupUsersRepository = assigneeGroupUsersRepository;
        this.assigneeGroupUsersMapper = assigneeGroupUsersMapper;
        this.currentUserService = currentUserService;
        this.validationServices = validationServices;
    }


    @Override
    public AssigneeGroupUsersResponseDto findById(Integer id) {

        AssigneeGroupUsers groupUsers = assigneeGroupUsersRepository.findById(id).orElse(null);
        
        AssigneeGroupUsersResponseDto responseResult = assigneeGroupUsersMapper.toResponseGroupUsers(groupUsers); 

        return responseResult;
    }

    @Override
    public CrAssigneeGroupUsersResponseDto addUserInAssigneeGroup(CrAssigneeGroupUsersRequestDto crAssigneeGroupUsersRequestDto) {
        logger.info("Called Add User in Assignee Group: {}", crAssigneeGroupUsersRequestDto);


        boolean existUser = validationServices.existsAssigneeGroupUsersByAssigneeUserId(
                crAssigneeGroupUsersRequestDto.getUserId(),
                crAssigneeGroupUsersRequestDto.getAssigneeGroupId());

        if (existUser) {
            throw new RuntimeException("User already exists In Assignee Group");
        }

        AssigneeGroupUsers assigneeGroupUsers = assigneeGroupUsersMapper.toAssigneeGroupUsersEntityDetails(crAssigneeGroupUsersRequestDto, currentUserService.getCurrentUserId());

        if (assigneeGroupUsers == null) {
            throw new RuntimeException("AssigneeGroupUsers is null");
        }

        assigneeGroupUsersRepository.save(assigneeGroupUsers);

        return assigneeGroupUsersMapper.toResponseCrGroupUsers(assigneeGroupUsers);
    }

    @Override
    public void removeUserFromAssigneeGroup(Integer id) {

        assigneeGroupUsersRepository.deleteById(id);

    }


}
package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.service.AssigneeGroupUserService;
import org.example.incidentmanagement.dto.createRequest.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateAssigneeGroupUsersResponseDto;
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

    public AssigneeGroupUserServiceImpl(AssigneeGroupUsersRepository assigneeGroupUsersRepository, AssigneeGroupUsersMapper assigneeGroupUsersMapper,
                                    CurrentUserService currentUserService) {
        this.assigneeGroupUsersRepository = assigneeGroupUsersRepository;
        this.assigneeGroupUsersMapper = assigneeGroupUsersMapper;
        this.currentUserService = currentUserService;
    }


    @Override
    public AssigneeGroupUsersResponseDto findById(Integer id) {

        AssigneeGroupUsers groupUsers = assigneeGroupUsersRepository.findById(id).orElse(null);
        
        AssigneeGroupUsersResponseDto responseResult = assigneeGroupUsersMapper.toResponseGroupUsers(groupUsers); 

        return responseResult;
    }

    @Override
    public CreateAssigneeGroupUsersResponseDto addUserInAssigneeGroup(CreateAssigneeGroupUsersRequestDto createAssigneeGroupUsersRequestDto) {
        logger.info("Called Add User in Assignee Group: {}", createAssigneeGroupUsersRequestDto);

        AssigneeGroupUsers assigneeGroupUsers = assigneeGroupUsersMapper.toAssigneeGroupUsersEntityDetails(createAssigneeGroupUsersRequestDto, currentUserService.getCurrentUserId());
        
        assigneeGroupUsersRepository.save(assigneeGroupUsers);

        return assigneeGroupUsersMapper.toResponseCrGroupUsers(assigneeGroupUsers);
    }

    @Override
    public void removeUserFromAssigneeGroup(Integer id) {

        assigneeGroupUsersRepository.deleteById(id);

    }


}
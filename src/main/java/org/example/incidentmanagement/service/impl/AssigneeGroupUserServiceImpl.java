package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.DefaultConverter;
import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.service.AssigneeGroupUserService;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
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
    private final DefaultConverter defaultConverter;

    public AssigneeGroupUserServiceImpl(AssigneeGroupUsersRepository assigneeGroupUsersRepository, AssigneeGroupUsersMapper assigneeGroupUsersMapper,
                                    CurrentUserService currentUserService, DefaultConverter defaultConverter) {
        this.assigneeGroupUsersRepository = assigneeGroupUsersRepository;
        this.assigneeGroupUsersMapper = assigneeGroupUsersMapper;
        this.currentUserService = currentUserService;
        this.defaultConverter = defaultConverter;
    }


    @Override
    public AssigneeGroupUsersResponseDto findById(Integer id) {

        AssigneeGroupUsers groupUsers = assigneeGroupRepository.findById(id);
        
        String fullName = defaultConverter.getUserFullName(groupUsers.getUserId);
        String groupName = defaultConverter.getAssigneeGroupName(groupUsers.getAssigneeGroupId);
        String createdBy = defaultConverter.getUserFullName(groupUsers.getCreatedBy);
        String updatedBy = defaultConverter.getUserFullName(groupUsers.getUpdatedBy);

        AssigneeGroupUsersResponseDto responseResult = assigneeGroupUsersMapper.toResponseGroupUsers(groupUsers, fullName, groupName, createdBy, updatedBy); 

        return responseResult;
    }

    @Override
    public CreateAssigneeGroupUsersResponseDto addUserInAssigneeGroup(CreateAssigneeGroupUsersRequestDto createAssigneeGroupUsersRequestDto) {
        logger.info("Called Add User in Assignee Group: {}", createAssigneeGroupUsersRequestDto);

        AssigneeGroupUsers assigneeGroupUsers = assigneeGroupUsersMapper.toAssigneeGroupUsersEntityDetails(createAssigneeGroupUsersRequestDto, currentUserService.getCurrentUserId());
        
        assigneeGroupUsersRepository.save(assigneeGroupUsers);

        String fullName = defaultConverter.getUserFullName(assigneeGroupUsers.getUserId);
        String groupName = defaultConverter.getAssigneeGroupName(assigneeGroupUsers.getAssigneeGroupId);

        return assigneeGroupUsersMapper.toResponseCrGroupUsers(assigneeGroupUsers, fullName, groupName);
    }

    @Override
    public void removeUserFromAssigneeGroup(Integer id) {

        assigneeGroupUsersRepository.deleteById(id);

    }




}

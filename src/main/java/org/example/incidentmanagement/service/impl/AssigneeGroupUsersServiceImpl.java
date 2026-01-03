package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.service.AssigneGroupService;
import org.example.incidentmanagement.service.CurrentUserService;

@Service
public class AssigneeGroupUsersImpl implements AssigneGroupService {

    private final AssigneeGroupUsersRepository assigneeGroupUsersRepository;
    private final AssigneeGroupUsersMapper assigneeGroupUsersMapper;
    private final CurrentUserService currentUserService;

    public AssigneeGroupUsersImpl(AssigneeGroupUsersRepository assigneeGroupUsersRepository, AssigneeGroupUsersMapper assigneeGroupUsersMapper,
                                    CurrentUserService currentUserService) {
        this.assigneeGroupUsersRepository = assigneeGroupUsersRepository;
        this.assigneeGroupUsersMapper = assigneeGroupUsersMapper;
        this.currentUserService = currentUserService;
    }


    @Override
    public AssigneeGroupUsersResponseDto findById(Integer id) {
        return null;
    }

    @Override
    public CreateAssigneeGroupUsersResponseDto addUserInAssigneeGroup(CreateAssigneeGroupUsersRequestDto createAssigneeGroupUsersRequestDto) {
        logger.info("Called Add User in Assignee Group: {}", createAssigneeGroupUsersRequestDto);

        AssigneeGroupUsers assigneeGroupUsers = assigneeGroupUsersMapper.toAssigneeGroupUsersEntityDetails(createAssigneeGroupUsersRequestDto, currentUserService.getCurrentUserId());
        assigneeGroupUsersRepository.save(assigneeGroupUsers);

        return assigneeGroupUsersMapper.toCreateAssigneeGroupUsersResponseDto(assigneeGroupUsers);
    }

    @Override
    public void removeUserFromAssigneeGroup(Integer id) {

    }




}

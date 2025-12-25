package org.example.incidentmanagement.service.impl;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.AssigneeGroupMapper;
import org.example.incidentmanagement.repository.AssigneeGroupRepository;
import org.example.incidentmanagement.service.AssigneGroupService;
import org.example.incidentmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssigneeGroupServiceImpl implements AssigneGroupService {

    Logger logger = LoggerFactory.getLogger(AssigneeGroupServiceImpl.class);
    private AssigneeGroupRepository assigneeGroupRepository;
    private AssigneeGroupMapper assigneeGroupMapper;
    private UserService userService;

    public AssigneeGroupServiceImpl(AssigneeGroupRepository assigneeGroupRepository,
                                    AssigneeGroupMapper assigneeGroupMapper,
                                    UserService userService) {
        this.assigneeGroupRepository = assigneeGroupRepository;
        this.assigneeGroupMapper = assigneeGroupMapper;
        this.userService = userService;
    }


    @Override
    public AssigneeGroupResponseDto findById(Integer id) {
        logger.info("Called Find AssigneeGroups by ID: {}", id);
        AssigneeGroups assigneeGroups = assigneeGroupRepository.findById(id).orElse(null);
        if (assigneeGroups == null) {
            logger.info("AssigneeGroups with ID {} not found", id);
            throw new CustomException(ResponseCodes.INVALID_ASSIGNEE_GROUP);
        }

        AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(assigneeGroups);

        //Get and Set Created By User
        if (assigneeGroupResult.getCreatedBy() != null) {
            String createdBy = userService.getFullName(assigneeGroups.getCreatedBy());

            if (createdBy != null) {
                assigneeGroupResult.setCreatedBy(createdBy);
            }
        }

        //Get and Set Updated By User
        if (assigneeGroupResult.getUpdatedBy() != null) {
            String updatedBy = userService.getFullName(assigneeGroups.getUpdatedBy());
            if (updatedBy != null) {
                assigneeGroupResult.setUpdatedBy(updatedBy);
            }
        }


        return assigneeGroupResult;
    }

    @Override
    public AssigneeGroupResponseDto findByGroupName(String groupName) {
        logger.info("Called Find AssigneeGroups by Group Name: {}", groupName);
        AssigneeGroups assigneeGroups = assigneeGroupRepository.findByGroupName(groupName);

        AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(assigneeGroups);

        //Get and Set Created By User

        if (assigneeGroupResult.getCreatedBy() != null) {
            String createdBy = userService.getFullName(assigneeGroups.getCreatedBy());

            if (createdBy != null) {
                assigneeGroupResult.setCreatedBy(createdBy);
            }
        }

        //Get and Set Updated By User
        if (assigneeGroupResult.getUpdatedBy() != null) {
            String updatedBy = userService.getFullName(assigneeGroups.getUpdatedBy());
            if (updatedBy != null) {
                assigneeGroupResult.setUpdatedBy(updatedBy);
            }
        }


        return assigneeGroupResult;

    }

    @Override
    public List<AssigneeGroupResponseDto> findAll() {
        logger.info("Called Find All AssigneeGroups");
        List<AssigneeGroups> groups = assigneeGroupRepository.findAll();

        //Get List With UserNames
        List<AssigneeGroupResponseDto> result = groups.stream()
                .map(group -> {
                    AssigneeGroupResponseDto assigneeGroupResult =
                            assigneeGroupMapper.toGroupResponseDto(group);

                    String createdBy = userService.getFullName(group.getCreatedBy());
                    String updatedBy = userService.getFullName(group.getUpdatedBy());

                    // Get and Set Created By User
                    if (createdBy != null) {
                        assigneeGroupResult.setCreatedBy(createdBy);
                    }
                     if (updatedBy != null) {
                         assigneeGroupResult.setUpdatedBy(updatedBy);
                     }

                    return assigneeGroupResult;
                })
                .toList();

        return result;
    }

    @Override
    public AssigneeGroupResponseDto createAssigneeGroup(CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto) {
        logger.info("Called Create AssigneeGroup: {}", createAssigneeGroupRequestDto.getGroupName());
        AssigneeGroups assigneeGroups = assigneeGroupMapper.toEntity(createAssigneeGroupRequestDto);
        assigneeGroups.setCreatedOn(LocalDateTime.now());
        assigneeGroups.setCreatedBy(11);
        assigneeGroupRepository.save(assigneeGroups);

        AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(assigneeGroups);

        String createdBy = userService.getFullName(assigneeGroups.getCreatedBy());
        String updatedBy = userService.getFullName(assigneeGroups.getUpdatedBy());

        if (createdBy != null) {
            assigneeGroupResult.setCreatedBy(createdBy);
        }
        if (updatedBy != null) {
            assigneeGroupResult.setUpdatedBy(updatedBy);
        }

        return assigneeGroupResult;
    }

    @Override
    public void deleteAssigneeGroup(Integer id) {
        AssigneeGroups assigneeGroups = assigneeGroupRepository.findById(id).orElse(null);

        logger.info("Called Delete AssigneeGroup: {} {}", id, assigneeGroups.getGroupName() );


        assigneeGroupRepository.delete(assigneeGroups);

    }
}
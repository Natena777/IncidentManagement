package org.example.incidentmanagement.service.impl;


import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateAssigneeGroupReqDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.AssigneeGroupMapper;
import org.example.incidentmanagement.repository.AssigneeGroupRepository;
import org.example.incidentmanagement.service.ValidationServices;
import org.example.incidentmanagement.service.interfaces.AssigneGroupService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneeGroupServiceImpl implements AssigneGroupService {

    Logger logger = LoggerFactory.getLogger(AssigneeGroupServiceImpl.class);
    private final AssigneeGroupRepository assigneeGroupRepository;
    private final AssigneeGroupMapper assigneeGroupMapper;
    private final CurrentUserService currentUserService;
    private final DefaultConverter defaultConverter;
    private final ValidationServices validationServices;

    public AssigneeGroupServiceImpl(AssigneeGroupRepository assigneeGroupRepository,
                                    AssigneeGroupMapper assigneeGroupMapper,
                                    DefaultConverter defaultConverter,
                                    CurrentUserService currentUserService,
                                    ValidationServices validationServices) {
        this.assigneeGroupRepository = assigneeGroupRepository;
        this.assigneeGroupMapper = assigneeGroupMapper;
        this.currentUserService = currentUserService;
        this.defaultConverter = defaultConverter;
        this.validationServices = validationServices;
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



        return assigneeGroupResult;
    }

    @Override
    public AssigneeGroupResponseDto findByGroupName(String groupName) {
        logger.info("Called Find AssigneeGroups by Group Name: {}", groupName);
        AssigneeGroups assigneeGroups = assigneeGroupRepository.findByGroupName(groupName).orElse(null);

        AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(assigneeGroups);



        return assigneeGroupResult;

    }

    @Override
    public List<AssigneeGroupResponseDto> findAll() {
        logger.info("Called Find All AssigneeGroups");
        List<AssigneeGroups> groups = assigneeGroupRepository.findAll();

        //Get List With UserNames
        List<AssigneeGroupResponseDto> result = groups.stream()
                .map(group -> {
                    AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(group);
                    return assigneeGroupResult;
                })
                .toList();

        return result;
    }

    @Override
    public AssigneeGroupResponseDto createAssigneeGroup(CrAssigneeGroupRequestDto crAssigneeGroupRequestDto) {
        logger.info("Called Create AssigneeGroup: {}", crAssigneeGroupRequestDto.getGroupName());
        AssigneeGroups assigneeGroups = assigneeGroupMapper.toEntityWithDefaults(crAssigneeGroupRequestDto, currentUserService.getCurrentUserId());
        assigneeGroupRepository.save(assigneeGroups);

        AssigneeGroupResponseDto assigneeGroupResult = assigneeGroupMapper.toGroupResponseDto(assigneeGroups);

        return assigneeGroupResult;
    }



    @Override
    public AssigneeGroupResponseDto updateAssigneeGroup(Integer id, UpdateAssigneeGroupReqDto updateAssigneeGroupReqDto) {

        AssigneeGroups assigneeGroups = assigneeGroupRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_ASSIGNEE_GROUP));

        assigneeGroupMapper.toUpdateEntity(updateAssigneeGroupReqDto, assigneeGroups);


        assigneeGroups.setUpdatedBy(currentUserService.getCurrentUserId());
        assigneeGroups.setUpdatedOn(defaultConverter.getDefaultTbilisiTime());
        AssigneeGroups saved = assigneeGroupRepository.save(assigneeGroups);
        return assigneeGroupMapper.toGroupResponseDto(saved);
    }

    @Override
    public void deleteAssigneeGroup(Integer id) {
        AssigneeGroups assigneeGroups = assigneeGroupRepository.findById(id).orElse(null);
        logger.info("Called Delete AssigneeGroup: {} {}", id, assigneeGroups.getGroupName());

        //Check Case Exist.
        boolean existCase = validationServices.existsCaseByAssigneeGroupId(id);
        if (existCase){
            throw new CustomException(ResponseCodes.ASSIGNEE_GROUP_HAVE_CASE);
        }

        //Check Group Users
        boolean existUser = validationServices.existUserInAssigneeGroup(id);
        if (existUser){
            throw new CustomException(ResponseCodes.ASSIGNEE_GROUP_HAVE_USERS);
        }

        //Check Service Catalog Services
        boolean existService = validationServices.existsScServiceByAssigneeGroupId(id);
        if (existService){
            throw new CustomException(ResponseCodes.ASSIGNEE_GROUP_HAVE_SERVICES);
        }

        assigneeGroupRepository.delete(assigneeGroups);
    }






}

package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.example.incidentmanagement.mappers.GroupCaseStatusMapper;
import org.example.incidentmanagement.repository.GroupCaseStatusRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.GroupCaseStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GroupCaseStatusServiceImpl implements GroupCaseStatusService {

    private final GroupCaseStatusRepository groupCaseStatusRepository;
    private final GroupCaseStatusMapper groupCaseStatusMapper;
    private final CurrentUserService currentUserService;
    Logger logger = LoggerFactory.getLogger(GroupCaseStatusServiceImpl.class);

    public GroupCaseStatusServiceImpl(GroupCaseStatusRepository groupCaseStatusRepository,
                                      GroupCaseStatusMapper groupCaseStatusMapper,
                                      CurrentUserService currentUserService) {
        this.groupCaseStatusRepository = groupCaseStatusRepository;
        this.groupCaseStatusMapper = groupCaseStatusMapper;
        this.currentUserService = currentUserService;
    }

    @Override
    public CrGroupCaseStatResponseDto findById(Integer id) {
        logger.info("Called Find By ID: {}", id);
        AssigneeGroupCaseStatus assigneeGroupCaseStatus = groupCaseStatusRepository.findById(id).orElse(null);

        CrGroupCaseStatResponseDto responseDto = groupCaseStatusMapper.toCaseStatusResponse(assigneeGroupCaseStatus);

        return responseDto;

    }

    @Override
    public CrGroupCaseStatResponseDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto) {
        logger.info("Called add AssigneeGroupCaseStatus: {}", crGroupCaseStatRequestDto);

        AssigneeGroupCaseStatus GroupCaseStatustoSave = groupCaseStatusMapper.toGroupCaseStatusEntityDefaults(crGroupCaseStatRequestDto,
                currentUserService.getCurrentUserId());

        groupCaseStatusRepository.save(GroupCaseStatustoSave);

        CrGroupCaseStatResponseDto responseDto = groupCaseStatusMapper.toCaseStatusResponse(GroupCaseStatustoSave);

        return responseDto;

    }

    @Override
    public void deleteCaseStatusOnGroup(Integer id) {
        logger.info("Called delete AssigneeGroupCaseStatus: {}", id);
        groupCaseStatusRepository.deleteById(id);

    }
}

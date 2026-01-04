package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.response.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.example.incidentmanagement.mappers.GroupCaseStatusMapper;
import org.example.incidentmanagement.repository.GroupCaseStatusRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.DefaultConverter;
import org.example.incidentmanagement.service.GroupCaseStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GroupCaseStatusServiceImpl implements GroupCaseStatusService {

    private GroupCaseStatusRepository groupCaseStatusRepository;
    private GroupCaseStatusMapper groupCaseStatusMapper;
    private DefaultConverter defaultConverter;
    private CurrentUserService currentUserService;
    Logger logger = LoggerFactory.getLogger(GroupCaseStatusServiceImpl.class);

    public GroupCaseStatusServiceImpl(GroupCaseStatusRepository groupCaseStatusRepository,
                                      GroupCaseStatusMapper groupCaseStatusMapper,
                                      DefaultConverter defaultConverter,
                                      CurrentUserService currentUserService) {
        this.groupCaseStatusRepository = groupCaseStatusRepository;
        this.groupCaseStatusMapper = groupCaseStatusMapper;
        this.defaultConverter = defaultConverter;
        this.currentUserService = currentUserService;
    }

    @Override
    public CrGroupCaseStatResponseDto findById(Integer id) {
        logger.info("Called Find By ID: {}", id);
        AssigneeGroupCaseStatus assigneeGroupCaseStatus = groupCaseStatusRepository.findById(id).orElse(null);

        String createdBy = defaultConverter.getUserFullName(assigneeGroupCaseStatus.getCreatedBy());
        String updatedBy = defaultConverter.getUserFullName(assigneeGroupCaseStatus.getUpdatedBy());
        String currentStatus = defaultConverter.getCaseStatusName(assigneeGroupCaseStatus.getCurrentStatusId());
        String previousStatus = defaultConverter.getCaseStatusName(assigneeGroupCaseStatus.getPreviousStatusId());
        String nextStatus = defaultConverter.getCaseStatusName(assigneeGroupCaseStatus.getNextStatusId());
        String assigneeGroup = defaultConverter.getAssigneeGroupName(assigneeGroupCaseStatus.getAssigneeGroupId());

        CrGroupCaseStatResponseDto responseDto = groupCaseStatusMapper.toCaseStatusResponseDetails(assigneeGroupCaseStatus,
                currentStatus, previousStatus, nextStatus, assigneeGroup, createdBy, updatedBy);

        return responseDto;

    }

    @Override
    public CrGroupCaseStatResponseDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto) {
        logger.info("Called add AssigneeGroupCaseStatus: {}", crGroupCaseStatRequestDto);

        AssigneeGroupCaseStatus GroupCaseStatustoSave = groupCaseStatusMapper.toGroupCaseStatusEntityDefaults(crGroupCaseStatRequestDto,
                currentUserService.getCurrentUserId());

        groupCaseStatusRepository.save(GroupCaseStatustoSave);

        String createdBy = defaultConverter.getUserFullName(GroupCaseStatustoSave.getCreatedBy());
        String updatedBy = defaultConverter.getUserFullName(GroupCaseStatustoSave.getUpdatedBy());
        String currentStatus = defaultConverter.getCaseStatusName(GroupCaseStatustoSave.getCurrentStatusId());
        String previousStatus = defaultConverter.getCaseStatusName(GroupCaseStatustoSave.getPreviousStatusId());
        String nextStatus = defaultConverter.getCaseStatusName(GroupCaseStatustoSave.getNextStatusId());
        String assigneeGroup = defaultConverter.getAssigneeGroupName(GroupCaseStatustoSave.getAssigneeGroupId());

        CrGroupCaseStatResponseDto responseDto = groupCaseStatusMapper.toCaseStatusResponseDetails(GroupCaseStatustoSave,
                currentStatus, previousStatus, nextStatus, assigneeGroup, createdBy, updatedBy);

        return responseDto;

    }

    @Override
    public void deleteCaseStatusOnGroup(Integer id) {
        logger.info("Called delete AssigneeGroupCaseStatus: {}", id);
        groupCaseStatusRepository.deleteById(id);

    }
}

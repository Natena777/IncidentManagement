package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateGroupCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.GroupCaseStatRespDto;
import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.GroupCaseStatusMapper;
import org.example.incidentmanagement.repository.GroupCaseStatusRepository;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.interfaces.GroupCaseStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupCaseStatusServiceImpl implements GroupCaseStatusService {

    private final GroupCaseStatusRepository groupCaseStatusRepository;
    private final GroupCaseStatusMapper groupCaseStatusMapper;
    private final CurrentUserService currentUserService;
    private final DefaultConverter defaultConverter;
    Logger logger = LoggerFactory.getLogger(GroupCaseStatusServiceImpl.class);

    public GroupCaseStatusServiceImpl(GroupCaseStatusRepository groupCaseStatusRepository,
                                      GroupCaseStatusMapper groupCaseStatusMapper,
                                      CurrentUserService currentUserService,
                                      DefaultConverter defaultConverter) {
        this.groupCaseStatusRepository = groupCaseStatusRepository;
        this.groupCaseStatusMapper = groupCaseStatusMapper;
        this.currentUserService = currentUserService;
        this.defaultConverter = defaultConverter;
    }

    @Override
    public GroupCaseStatRespDto findById(Integer id) {
        logger.info("Called Find By ID: {}", id);
        AssigneeGroupCaseStatus assigneeGroupCaseStatus = groupCaseStatusRepository.findById(id).orElse(null);

        GroupCaseStatRespDto responseDto = groupCaseStatusMapper.toCaseStatusResponse(assigneeGroupCaseStatus);

        return responseDto;

    }

    @Override
    public GroupCaseStatRespDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto) {
        logger.info("Called add AssigneeGroupCaseStatus: {}", crGroupCaseStatRequestDto);

        AssigneeGroupCaseStatus GroupCaseStatustoSave = groupCaseStatusMapper.toGroupCaseStatusEntityDefaults(crGroupCaseStatRequestDto,
                currentUserService.getCurrentUserId());

        groupCaseStatusRepository.save(GroupCaseStatustoSave);

        GroupCaseStatRespDto responseDto = groupCaseStatusMapper.toCaseStatusResponse(GroupCaseStatustoSave);

        return responseDto;

    }

    @Override
    public GroupCaseStatRespDto updateGroupCaseStatus(Integer id, UpdateGroupCaseStatusReqDto updateGroupCaseStatusReqDto) {
        AssigneeGroupCaseStatus groupCaseStatus = groupCaseStatusRepository.findById(id)
                .orElseThrow(()-> new CustomException(ResponseCodes.INVALID_CASE_STATUS));

        groupCaseStatusMapper.toUpdateGroupCaseStatus(updateGroupCaseStatusReqDto, groupCaseStatus);


        groupCaseStatus.setUpdatedOn(defaultConverter.getDefaultTbilisiTime());
        groupCaseStatus.setUpdatedBy(currentUserService.getCurrentUserId());

        AssigneeGroupCaseStatus savedGroup = groupCaseStatusRepository.save(groupCaseStatus);
        return groupCaseStatusMapper.toCaseStatusResponse(savedGroup);
    }

    @Override
    public void deleteCaseStatusOnGroup(Integer id) {
        logger.info("Called delete AssigneeGroupCaseStatus: {}", id);
        groupCaseStatusRepository.deleteById(id);

    }
}

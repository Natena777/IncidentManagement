package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.entity.Cases;
import org.example.incidentmanagement.entity.ScServices;
import org.example.incidentmanagement.enums.RequestTimeUnitEnums;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.CaseMapper;
import org.example.incidentmanagement.repository.CaseRepository;
import org.example.incidentmanagement.repository.ScServicesRepository;
import org.example.incidentmanagement.service.interfaces.CaseService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.service.TimeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final CurrentUserService currentUserService;
    private final CaseMapper caseMapper;
    private final ScServicesRepository scServicesRepository;
    private final DefaultConverter defaultConverter;
    private final TimeCalculator timeCalculator;
    Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);

    public CaseServiceImpl(CaseRepository caseRepository, CurrentUserService currentUserService,
                           CaseMapper caseMapper, ScServicesRepository scServicesRepository,
                           DefaultConverter defaultConverter, TimeCalculator timeCalculator) {
        this.caseRepository = caseRepository;
        this.currentUserService = currentUserService;
        this.caseMapper = caseMapper;
        this.scServicesRepository = scServicesRepository;
        this.defaultConverter = defaultConverter;
        this.timeCalculator = timeCalculator;
    }


    @Override
    public void deleteCase(Integer id) {
        logger.info("Called Delete Case With ID: {}", id);
        caseRepository.deleteById(id);

    }

    @Override
    public CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto) {
        logger.info("Called Create Case");
        Integer assigneeGroup = scServicesRepository.findById(createCaseRequestDto.getScServiceId()).orElse(null).getAssigneeGroupId();


        if (assigneeGroup == null) {
            throw new CustomException(ResponseCodes.INVALID_ASSIGNEE_GROUP);
        }


        Cases cases = caseMapper.toCaseEntity(createCaseRequestDto,currentUserService.getCurrentUserId(), assigneeGroup);

        //Calculate Response And Resolution Time
        LocalDateTime calculatedResponseTime = timeCalculator.calculateResponseTimeByService(
                cases.getScServiceId(),
                cases.getCreatedOn(),
                cases.getAssigneeGroupId()
        );

        LocalDateTime calculatedResolutionTime = timeCalculator.calculateResolutionTimeByService(
                cases.getScServiceId(),
                cases.getCreatedOn(),
                cases.getAssigneeGroupId()
        );


        //Save
        cases.setResponseTimeCalculated(calculatedResponseTime);
        cases.setResolutionTimeCalculated(calculatedResolutionTime);
        caseRepository.saveAndFlush(cases);


        CreateCaseResponseDto result = caseMapper.toCreateCaseResponse(cases);
        return result;
    }


}

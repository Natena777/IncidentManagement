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

        // Response Time-ის გამოთვლა
        LocalDateTime calculatedResponseTime = calculateResponseTime(
                cases.getScServiceId(),
                cases.getCreatedOn(),
                cases.getAssigneeGroupId()
        );

        //Save
        cases.setResponseTimeCalculated(calculatedResponseTime);
        caseRepository.saveAndFlush(cases);


        CreateCaseResponseDto result = caseMapper.toCreateCaseResponse(cases);
        return result;
    }


    private LocalDateTime calculateResponseTime(Integer serviceId,
                                                LocalDateTime createdOn,
                                                Integer assigneeGroupId) {
        logger.info("Calculating response time for serviceId={}, createdOn={}, groupId={}",
                serviceId, createdOn, assigneeGroupId);

        ScServices serviceItem = scServicesRepository.findById(serviceId)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));

        logger.info("Service found: {}, ResponseTimeType: {}",
                serviceItem.getServicesName(), serviceItem.getResponseTimeType());

        int responseMinutes = defaultConverter.convertTimeUnitToMinutes(
                serviceItem.getResponseTimeValue(),
                serviceItem.getResponseTimeType()
        );

        RequestTimeUnitEnums responseType = serviceItem.getResponseTimeType();

        if (responseType == RequestTimeUnitEnums.WORKING_MINUTES ||
                responseType == RequestTimeUnitEnums.WORKING_HOURS ||
                responseType == RequestTimeUnitEnums.WORKING_DAYS) {

            logger.info("Using working hours calculation with {} minutes", responseMinutes);
            return timeCalculator.calculateWorkingHoursDeadline(
                    createdOn,
                    responseMinutes,
                    assigneeGroupId
            );
        }

        logger.info("Using calendar time, adding {} minutes", responseMinutes);
        return createdOn.plusMinutes(responseMinutes);
    }




}

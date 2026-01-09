package org.example.incidentmanagement.service.impl;

import jakarta.transaction.Transactional;
import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.entity.Cases;
import org.example.incidentmanagement.entity.ScServices;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.CaseMapper;
import org.example.incidentmanagement.repository.CaseRepository;
import org.example.incidentmanagement.repository.DatabaseOperations;
import org.example.incidentmanagement.repository.ScServicesRepository;
import org.example.incidentmanagement.service.interfaces.CaseService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.service.TimeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @Transactional
    public CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto) {

        Integer currentUser = currentUserService.getCurrentUserId();
        LocalDateTime currentDate = defaultConverter.getDefaultTbilisiTime();

        logger.info("Creating case for service ID: {}, user: {}",
                createCaseRequestDto.getScServiceId(), currentUser);

        ScServices service = scServicesRepository.findById(createCaseRequestDto.getScServiceId())
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));

        Integer assigneeGroup = service.getAssigneeGroupId();

        if (assigneeGroup == null) {
            throw new CustomException(ResponseCodes.INVALID_ASSIGNEE_GROUP);
        }

        Cases cases = caseMapper.toCaseEntity(createCaseRequestDto, currentUser, assigneeGroup);

        // Calculate Response And Resolution Time
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

        // Save Case
        cases.setResponseTimeCalculated(calculatedResponseTime);
        cases.setResolutionTimeCalculated(calculatedResolutionTime);
        caseRepository.saveAndFlush(cases);

        // Add Case Access Rights
        try (DatabaseOperations databaseOperations = new DatabaseOperations()) {

            databaseOperations.insertCasesAccessRight(
                    cases.getId(), currentUser, null, null,
                    currentUser, currentDate, true, true, true
            );

            databaseOperations.insertCasesAccessRight(
                    cases.getId(), null, null, assigneeGroup,
                    currentUser, currentDate, true, true, false
            );

        } catch (Exception e) {
            logger.error("Failed to create case access rights for case ID: {}", cases.getId(), e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }

        CreateCaseResponseDto result = caseMapper.toCreateCaseResponse(cases);

        logger.info("Case created successfully with ID: {}", result.getId());

        return result;
    }



    @Override
    public List<Map<String, Object>> getAllCaseAccessRights(Integer caseId) {
        try (DatabaseOperations databaseOperations = new DatabaseOperations()) {
            List<Map<String, Object>> accessRights = databaseOperations.getCasesAccessRights(caseId);
            return accessRights;
        } catch (Exception e) {
            logger.error("Failed to get access rights for case ID: {}", caseId, e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }
    }

    @Override
    public void deleteCaseAccessRights(Integer caseId, Integer accessRightId) {
        try (DatabaseOperations databaseOperations = new DatabaseOperations()) {

            List<Map<String, Object>> accessRights = databaseOperations.getCasesAccessRights(caseId);

            if (accessRights.isEmpty()) {
                throw new RuntimeException("There are no access rights for this case");
            }

            databaseOperations.deleteCasesAccessRights(accessRightId);
            logger.info("Deleted access right ID: {} for case ID: {}", accessRightId, caseId);

        } catch (Exception e) {
            logger.error("Failed to delete access right ID: {} for case ID: {}", accessRightId, caseId, e);
            throw new CustomException(ResponseCodes.FAILED_DATABASE_MANUAL_OPERATION, e.getMessage());
        }
    }


}
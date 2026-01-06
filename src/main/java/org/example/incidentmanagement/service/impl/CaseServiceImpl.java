package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.entity.Cases;
import org.example.incidentmanagement.mappers.CaseMapper;
import org.example.incidentmanagement.repository.CaseRepository;
import org.example.incidentmanagement.service.CaseService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final CurrentUserService currentUserService;
    private final CaseMapper caseMapper;
    Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);

    public CaseServiceImpl(CaseRepository caseRepository, CurrentUserService currentUserService,
                           CaseMapper caseMapper) {
        this.caseRepository = caseRepository;
        this.currentUserService = currentUserService;
        this.caseMapper = caseMapper;
    }

    @Override
    public CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto) {
        logger.info("Called Create Case");
        Cases cases = caseMapper.toCaseEntity(createCaseRequestDto, currentUserService.getCurrentUserId());
        caseRepository.save(cases);

        CreateCaseResponseDto result = caseMapper.toCreateCaseResponse(cases);
        return result;
    }

    @Override
    public void deleteCase(Integer id) {
        logger.info("Called Delete Case With ID: {}", id);
        caseRepository.deleteById(id);

    }
}

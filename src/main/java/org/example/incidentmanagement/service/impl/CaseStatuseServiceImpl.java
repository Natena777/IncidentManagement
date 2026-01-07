package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CrCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatusesResponseDto;
import org.example.incidentmanagement.entity.CaseStatuses;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.mappers.CaseStatusMapper;
import org.example.incidentmanagement.repository.CaseStatusesRepository;
import org.example.incidentmanagement.service.interfaces.CaseStatuseService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseStatuseServiceImpl implements CaseStatuseService {

    Logger logger = LoggerFactory.getLogger(CaseStatuseServiceImpl.class);
    private final CaseStatusesRepository statusesRepository;
    private final CaseStatusMapper caseStatusMapper;
    private final CurrentUserService currentUserService;
    private final DefaultConverter defaultConverter;

    public CaseStatuseServiceImpl(CaseStatusesRepository statusesRepository,
                                  CaseStatusMapper caseStatusMapper,
                                  CurrentUserService currentUserService,
                                  DefaultConverter defaultConverter) {
        this.statusesRepository = statusesRepository;
        this.caseStatusMapper = caseStatusMapper;
        this.currentUserService = currentUserService;
        this.defaultConverter = defaultConverter;
    }

    @Override
    public CaseStatusesResponseDto findByStatus(String status) {

        logger.info("Called Find by status with statusName {}", status);
        if (status == null) {
              throw new IllegalArgumentException("Status cannot be null");
        }
        CaseStatuses caseStatuses = statusesRepository.findBystatusName(status);

        if (caseStatuses == null) {
            throw new IllegalArgumentException("Case Statuses not found");
        }

        CaseStatusesResponseDto statusResult = caseStatusMapper.toCaseStatusResponseDto(caseStatuses);
    
        return statusResult;

    }

    @Override
    public List<CaseStatusesResponseDto> findAllStatuses() {

        logger.info("Called Find all statuses");
        List<CaseStatuses> caseStatuses = statusesRepository.findAll();
        List<CaseStatusesResponseDto> caseStatusesResponseDtos = caseStatuses.stream()
                .map(status ->{
                    CaseStatusesResponseDto statusResult = caseStatusMapper.toCaseStatusResponseDto(status);
                    return statusResult;
                })
                .toList();

        return caseStatusesResponseDtos;
    }

    @Override
    public CrCaseStatusesResponseDto createCaseStatuses(CrCaseStatusesRequestDto crCaseStatusesRequestDto) {
        logger.info("Called Create Case Status");

        CaseStatuses casetoSave = caseStatusMapper.toCaseStatusEntityDefaults(crCaseStatusesRequestDto,
                                                                            currentUserService.getCurrentUserId());
 

        statusesRepository.save(casetoSave);

        CrCaseStatusesResponseDto statusResponse = caseStatusMapper.toCreateCaseStatusResponseDto(casetoSave);

        return statusResponse;
    }

    @Override
    public CaseStatusesResponseDto updateCaseStatus(Integer id, UpdateCaseStatusReqDto updateCaseStatusReqDto) {
        CaseStatuses status = statusesRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_CASE_STATUS));

        caseStatusMapper.updateCaseStatusEntity(updateCaseStatusReqDto, status);



        status.setUpdatedBy(currentUserService.getCurrentUserId());
        status.setUpdatedOn(defaultConverter.getDefaultTbilisiTime());
        CaseStatuses saved = statusesRepository.save(status);
        return caseStatusMapper.toCaseStatusResponseDto(saved);
    }



    @Override
    public void deleteCaseStatuses(Integer id) {
        logger.info("Called Delete Case Statuses");
        statusesRepository.deleteById(id);

    }
}

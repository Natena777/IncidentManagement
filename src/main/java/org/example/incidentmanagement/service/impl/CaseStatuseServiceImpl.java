package org.example.incidentmanagement.service.impl;

import org.example.incidentmanagement.dto.requests.CreateCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.response.CreateCaseStatusesResponseDto;
import org.example.incidentmanagement.entity.CaseStatuses;
import org.example.incidentmanagement.mappers.CaseStatusMapper;
import org.example.incidentmanagement.repository.CaseStatusesRepository;
import org.example.incidentmanagement.service.CaseStatuseService;
import org.example.incidentmanagement.service.CurrentUserService;
import org.example.incidentmanagement.service.DefaultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseStatuseServiceImpl implements CaseStatuseService {

    Logger logger = LoggerFactory.getLogger(CaseStatuseServiceImpl.class);
    private CaseStatusesRepository statusesRepository;
    private DefaultConverter defaultConverter;
    private CaseStatusMapper caseStatusMapper;
    private CurrentUserService currentUserService;

    public CaseStatuseServiceImpl(CaseStatusesRepository statusesRepository,
                                  DefaultConverter defaultConverter,
                                  CaseStatusMapper caseStatusMapper,
                                  CurrentUserService currentUserService) {
        this.statusesRepository = statusesRepository;
        this.defaultConverter = defaultConverter;
        this.caseStatusMapper = caseStatusMapper;
        this.currentUserService = currentUserService;
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

        String createdBy = defaultConverter.getUserFullName(caseStatuses.getCreatedBy());
        String updatedBy = defaultConverter.getUserFullName(caseStatuses.getUpdatedBy());

        CaseStatusesResponseDto statusResult = new CaseStatusesResponseDto();
        statusResult.setCreatedBy(createdBy);
        statusResult.setUpdatedBy(updatedBy);

        return statusResult;

    }

    @Override
    public List<CaseStatusesResponseDto> findAllStatuses() {

        logger.info("Called Find all statuses");
        List<CaseStatuses> caseStatuses = statusesRepository.findAll();
        List<CaseStatusesResponseDto> caseStatusesResponseDtos = caseStatuses.stream()
                .map(status ->{
                    CaseStatusesResponseDto statusResult = caseStatusMapper.toCaseStatusResponseDto(status);
                    //get creator and update fullname
                    String createdBy = defaultConverter.getUserFullName(status.getCreatedBy());
                    String updatedBy = defaultConverter.getUserFullName(status.getUpdatedBy());

                    //set creator and update fullname
                    statusResult.setCreatedBy(createdBy);
                    statusResult.setUpdatedBy(updatedBy);

                    return statusResult;
                })
                .toList();

        return caseStatusesResponseDtos;
    }

    @Override
    public CreateCaseStatusesResponseDto createCaseStatuses(CreateCaseStatusesRequestDto createCaseStatusesRequestDto) {
        logger.info("Called Create Case Status");
        String isFinal = defaultConverter.booleanToString(createCaseStatusesRequestDto.getIsFinal());
        String isPaused = defaultConverter.booleanToString(createCaseStatusesRequestDto.getIsPaused());

        CaseStatuses casetoSave = caseStatusMapper.toCaseStatusEntityDefaults(createCaseStatusesRequestDto, 
                                                                            currentUserService.getCurrentUserId(),
                                                                            isFinal,
                                                                            isPaused);
 

        statusesRepository.save(casetoSave);

        CreateCaseStatusesResponseDto statusResponse = caseStatusMapper.toCreateCaseStatusResponseDto(casetoSave);

        return statusResponse;
    }

    @Override
    public void deleteCaseStatuses(Integer id) {
        logger.info("Called Delete Case Statuses");
        statusesRepository.deleteById(id);

    }
}

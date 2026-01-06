package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.createRequest.CreateCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseStatusesResponseDto;

import java.util.List;

public interface CaseStatuseService {

    CaseStatusesResponseDto findByStatus(String status);
    List<CaseStatusesResponseDto> findAllStatuses();
    CreateCaseStatusesResponseDto createCaseStatuses(CreateCaseStatusesRequestDto createCaseStatusesRequestDto);
    void deleteCaseStatuses(Integer id);



}

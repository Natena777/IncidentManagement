package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatusesResponseDto;

import java.util.List;

public interface CaseStatuseService {

    CaseStatusesResponseDto findByStatus(String status);
    List<CaseStatusesResponseDto> findAllStatuses();
    CrCaseStatusesResponseDto createCaseStatuses(CrCaseStatusesRequestDto crCaseStatusesRequestDto);
    CaseStatusesResponseDto updateCaseStatus(Integer id, UpdateCaseStatusReqDto updateCaseStatusReqDto);
    void deleteCaseStatuses(Integer id);



}

package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;

public interface GroupCaseStatusService {

    CrGroupCaseStatResponseDto findById(Integer id);
    CrGroupCaseStatResponseDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto);
    void deleteCaseStatusOnGroup(Integer id);
}

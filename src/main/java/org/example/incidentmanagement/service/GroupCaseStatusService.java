package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.response.CrGroupCaseStatResponseDto;

public interface GroupCaseStatusService {

    CrGroupCaseStatResponseDto findById(Integer id);
    CrGroupCaseStatResponseDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto);
    void deleteCaseStatusOnGroup(Integer id);
}

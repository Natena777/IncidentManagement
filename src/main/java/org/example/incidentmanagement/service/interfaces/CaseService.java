package org.example.incidentmanagement.service.interfaces;


import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;

import java.util.List;
import java.util.Map;

public interface CaseService {

    CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto);
    void deleteCase(Integer id);


    List<Map<String, Object>> getAllCaseAccessRights(Integer caseId);
    void deleteCaseAccessRights(Integer caseId, Integer accessRightId);


}

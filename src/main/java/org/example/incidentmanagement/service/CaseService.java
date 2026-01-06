package org.example.incidentmanagement.service;


import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;

public interface CaseService {

    CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto);
    void deleteCase(Integer id);

}

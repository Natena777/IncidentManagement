package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.response.CreateCaseStatusesResponseDto;
import org.example.incidentmanagement.entity.CaseStatuses;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CaseStatusMapper {


    CreateCaseStatusesResponseDto toCreateCaseStatusResponseDto(CaseStatuses caseStatuses);

    CaseStatusesResponseDto toCaseStatusResponseDto(CaseStatuses caseStatuses);

    CaseStatuses toCaseStatusEntity(CreateCaseStatusesRequestDto createCaseStatusesRequestDto);

}

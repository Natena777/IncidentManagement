package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.requests.CreateCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.response.CreateCaseStatusesResponseDto;
import org.example.incidentmanagement.entity.CaseStatuses;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface CaseStatusMapper {


    //Default Response Of Case statuses
    CaseStatusesResponseDto toCaseStatusResponseDto(CaseStatuses caseStatuses);



    //Create Case Statuses Request And Responses
    CaseStatuses toCaseStatusEntity(CreateCaseStatusesRequestDto createCaseStatusesRequestDto);

    default CaseStatuses toCaseStatusEntityDefaults(CreateCaseStatusesRequestDto createCaseStatusesRequestDto, 
                                                    Integer currentUserId,
                                                    String isFinal,
                                                    String isPaused){
        CaseStatuses entity = toCaseStatusEntity(createCaseStatusesRequestDto);
        entity.setActive("Y");
        entity.setCreatedBy(currentUserId);
        entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
        entity.setIsFinal(isFinal);
        entity.setIsPaused(isPaused);
        return entity;
    }

    CreateCaseStatusesResponseDto toCreateCaseStatusResponseDto(CaseStatuses caseStatuses);


}

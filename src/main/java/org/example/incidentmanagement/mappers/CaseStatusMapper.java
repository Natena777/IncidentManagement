package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.dto.createRequest.CrCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatusesResponseDto;
import org.example.incidentmanagement.entity.CaseStatuses;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface CaseStatusMapper {


    //Default Response Of Case statuses
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "updatedBy", target = "updatedBy", qualifiedByName = "userIdToFullName")
    CaseStatusesResponseDto toCaseStatusResponseDto(CaseStatuses caseStatuses);



    //Create Case Statuses Request And Responses
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "Y")
    @Mapping(source = "isFinal", target = "isFinal", qualifiedByName = "booleanToString")
    @Mapping(source = "isPaused", target = "isPaused", qualifiedByName = "booleanToString")
    CaseStatuses toCaseStatusEntity(CrCaseStatusesRequestDto crCaseStatusesRequestDto);
        default CaseStatuses toCaseStatusEntityDefaults(CrCaseStatusesRequestDto crCaseStatusesRequestDto,
                                                        Integer currentUserId){
            CaseStatuses entity = toCaseStatusEntity(crCaseStatusesRequestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return entity;
        }

    CrCaseStatusesResponseDto toCreateCaseStatusResponseDto(CaseStatuses caseStatuses);


}

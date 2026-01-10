package org.example.incidentmanagement.mappers;

import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createRequest.CrCaseCommentRequestDto;
import org.example.incidentmanagement.dto.response.CaseCommentRespDto;
import org.example.incidentmanagement.entity.CaseComments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring",
        uses = {DefaultConverter.class})
public interface CaseCommentMapper {


    @Mapping(source = "caseId", target = "caseNumber", qualifiedByName = "getCaseNumber")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userIdToFullName")
    @Mapping(source = "modifiedBy", target = "modifiedBy", qualifiedByName = "userIdToFullName")
    CaseCommentRespDto toCaseCommentResponse(CaseComments caseComments);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    CaseComments toEntity(CrCaseCommentRequestDto requestDto);
        default CaseComments toCaseCommentEntity(CrCaseCommentRequestDto requestDto, Integer caseId, Integer currentUserId) {
            CaseComments caseComments = toEntity(requestDto);
            caseComments.setCaseId(caseId);
            caseComments.setCreatedBy(currentUserId);
            caseComments.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            return caseComments;
        }

}




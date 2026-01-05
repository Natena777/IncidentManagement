package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.entity.CaseStatChangePerm;
import org.example.incidentmanagement.dto.response.CrCaseStatChangePermRespDto;
import org.example.incidentmanagement.dto.requests.CrCasesStatChangePermRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface CaseStatChangeMapper {

    @Mapping(target = "canRead", ignore = true)
    @Mapping(target = "canEdit", ignore = true)
    @Mapping(target = "canDelete", ignore = true)
    @Mapping(target = "canChange", ignore = true)
    CaseStatChangePerm toEntity (CrCasesStatChangePermRequestDto crCasesStatChangePermRequestDto);
        default CaseStatChangePerm toCaseStatChangePermEntity(CrCasesStatChangePermRequestDto crCasesStatChangePermRequestDto,
                                                              Integer currentUserId,
                                                              Integer canEdit,
                                                              Integer canRead,
                                                              Integer canDelete,
                                                              Integer canChange){
            CaseStatChangePerm entity = toEntity(crCasesStatChangePermRequestDto);
            entity.setActive("A");
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setCanEdit(canEdit);
            entity.setCanRead(canRead);
            entity.setCanDelete(canDelete);
            entity.setCanChange(canChange);
            return entity;
        }


    @Mapping(target = "canRead", ignore = true)
    @Mapping(target = "canEdit", ignore = true)
    @Mapping(target = "canDelete", ignore = true)
    @Mapping(target = "canChange", ignore = true)
    CrCaseStatChangePermRespDto toCreateResponse (CaseStatChangePerm caseStatChangePerm);
        default CrCaseStatChangePermRespDto toCreateCaseStatusResponse(CaseStatChangePerm caseStatChangePerm,
                                                                       boolean canRead,
                                                                       boolean canEdit,
                                                                       boolean canDelete,
                                                                       boolean canChange) {
            CrCaseStatChangePermRespDto createResponse = toCreateResponse(caseStatChangePerm);
            createResponse.setCanRead(canRead);
            createResponse.setCanEdit(canEdit);
            createResponse.setCanDelete(canDelete);
            createResponse.setCanChange(canChange);
            return createResponse;
        }



}

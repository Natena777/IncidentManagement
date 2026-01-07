package org.example.incidentmanagement.mappers;


import org.example.incidentmanagement.entity.CaseStatChangePerm;
import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatChangePermRespDto;
import org.example.incidentmanagement.dto.createRequest.CrCasesStatChangePermRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring", uses = {DefaultConverter.class})
public interface CaseStatChangeMapper {

    @Mapping(target = "canRead", ignore = true)
    @Mapping(target = "canEdit", ignore = true)
    @Mapping(target = "canDelete", ignore = true)
    @Mapping(target = "canChange", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedOn", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "active", constant = "A")
    CaseStatChangePerm toEntity (CrCasesStatChangePermRequestDto crCasesStatChangePermRequestDto);
        default CaseStatChangePerm toCaseStatChangePermEntity(CrCasesStatChangePermRequestDto crCasesStatChangePermRequestDto,
                                                              Integer currentUserId,
                                                              Integer canEdit,
                                                              Integer canRead,
                                                              Integer canDelete,
                                                              Integer canChange){
            CaseStatChangePerm entity = toEntity(crCasesStatChangePermRequestDto);
            entity.setCreatedBy(currentUserId);
            entity.setCreatedOn(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
            entity.setCanEdit(canEdit);
            entity.setCanRead(canRead);
            entity.setCanDelete(canDelete);
            entity.setCanChange(canChange);
            return entity;
        }


    @Mapping(source = "canRead", target = "canRead", qualifiedByName = "IntegerToBoolean")
    @Mapping(source = "canEdit", target = "canEdit", qualifiedByName = "IntegerToBoolean")
    @Mapping(source = "canDelete",target = "canDelete", qualifiedByName = "IntegerToBoolean")
    @Mapping(source = "canChange", target = "canChange", qualifiedByName = "IntegerToBoolean")
    @Mapping(target = "assigneeGroup", ignore = true)
    CrCaseStatChangePermRespDto toCrCaseStatusResponse (CaseStatChangePerm caseStatChangePerm);




}

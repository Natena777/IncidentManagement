package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrCasesStatChangePermRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatChangePermRespDto;

public interface CaseStatChangePermService {

    CrCaseStatChangePermRespDto addPermission(CrCasesStatChangePermRequestDto requestDto);
    void removePermission(Integer id);

}

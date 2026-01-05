package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CrCasesStatChangePermRequestDto;
import org.example.incidentmanagement.dto.response.CrCaseStatChangePermRespDto;

public interface CaseStatChangePermService {

    CrCaseStatChangePermRespDto addPermission(CrCasesStatChangePermRequestDto requestDto);
    void removePermission(Integer id);

}

package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateGroupCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.GroupCaseStatRespDto;

public interface GroupCaseStatusService {

    GroupCaseStatRespDto findById(Integer id);
    GroupCaseStatRespDto addCaseStatusOnGroup(CrGroupCaseStatRequestDto crGroupCaseStatRequestDto);
    GroupCaseStatRespDto updateGroupCaseStatus(Integer id, UpdateGroupCaseStatusReqDto updateGroupCaseStatusReqDto);
    void deleteCaseStatusOnGroup(Integer id);
}

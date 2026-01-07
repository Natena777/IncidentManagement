package org.example.incidentmanagement.service.interfaces;

import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateAssigneeGroupReqDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;

import java.util.List;

public interface AssigneGroupService {

    AssigneeGroupResponseDto findById(Integer id);
    AssigneeGroupResponseDto findByGroupName(String groupName);
    List<AssigneeGroupResponseDto> findAll();
    AssigneeGroupResponseDto createAssigneeGroup(CrAssigneeGroupRequestDto crAssigneeGroupRequestDto);
    AssigneeGroupResponseDto updateAssigneeGroup(Integer id, UpdateAssigneeGroupReqDto updateAssigneeGroupReqDto);
    void deleteAssigneeGroup(Integer id);

}

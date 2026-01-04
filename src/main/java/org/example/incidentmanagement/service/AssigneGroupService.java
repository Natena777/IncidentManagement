package org.example.incidentmanagement.service;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;

import java.util.List;

public interface AssigneGroupService {

    AssigneeGroupResponseDto findById(Integer id);
    AssigneeGroupResponseDto findByGroupName(String groupName);
    List<AssigneeGroupResponseDto> findAll();
    AssigneeGroupResponseDto createAssigneeGroup(CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto);
    void deleteAssigneeGroup(Integer id);

}

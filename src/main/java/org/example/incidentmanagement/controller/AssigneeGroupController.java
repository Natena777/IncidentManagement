package org.example.incidentmanagement.controller;

import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.service.AssigneGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assigneeGroup")
public class AssigneeGroupController {

    @Autowired
    AssigneGroupService assigneGroupService;

    @PostMapping("/create")
    public AssigneeGroupResponseDto createAssigneGroup(@RequestBody CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto){
        AssigneeGroupResponseDto result = assigneGroupService.createAssigneeGroup(createAssigneeGroupRequestDto);
        return result;
    }


}

package org.example.incidentmanagement.controller;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.AssigneGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAssigneGroup(@PathVariable Integer id) {
        assigneGroupService.deleteAssigneeGroup(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getCode(),
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getMessage());

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping
    public List<AssigneeGroupResponseDto> findall(){
        return assigneGroupService.findAll();
    };


    @GetMapping("/id/{id}")
    public AssigneeGroupResponseDto findById(@PathVariable Integer id) {
        return assigneGroupService.findById(id);
    }

    @GetMapping("/name/{groupName}")
    public AssigneeGroupResponseDto findByGroupName(@PathVariable String groupName) {
        return assigneGroupService.findByGroupName(groupName);
    }
}
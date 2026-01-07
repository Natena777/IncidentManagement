package org.example.incidentmanagement.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateAssigneeGroupReqDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroups;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.interfaces.AssigneGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assigneeGroup")
@Tag(name = "Assignee Group Management API",
        description = "Manage Assigne Group In System")
public class AssigneeGroupController {


    private final AssigneGroupService assigneGroupService;

    public AssigneeGroupController(AssigneGroupService assigneGroupService) {
        this.assigneGroupService = assigneGroupService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create New Assignee Group")
    public AssigneeGroupResponseDto createAssigneGroup(@RequestBody CrAssigneeGroupRequestDto crAssigneeGroupRequestDto){
        AssigneeGroupResponseDto result = assigneGroupService.createAssigneeGroup(crAssigneeGroupRequestDto);
        return result;
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Update Assignee Group")
    public ResponseEntity<AssigneeGroupResponseDto> updateAssigneeGroup(@PathVariable Integer id, @RequestBody UpdateAssigneeGroupReqDto updateAssigneeGroupReqDto){
        AssigneeGroupResponseDto response = assigneGroupService.updateAssigneeGroup(id, updateAssigneeGroupReqDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Assignee Group By ID")
    public ResponseEntity<ApiResponse> deleteAssigneGroup(@PathVariable Integer id) {
        assigneGroupService.deleteAssigneeGroup(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getCode(),
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getMessage());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    @Operation(summary = "Find All Assignee Group")
    public List<AssigneeGroupResponseDto> findall(){
        return assigneGroupService.findAll();
    };


    @GetMapping("/id/{id}")
    @Operation(summary = "Find Assignee Group By ID")
    public AssigneeGroupResponseDto findById(@PathVariable Integer id) {
        return assigneGroupService.findById(id);
    }

    @GetMapping("/name/{groupName}")
    @Operation(summary = "Find Assignee Group By Name")
    public AssigneeGroupResponseDto findByGroupName(@PathVariable String groupName) {
        return assigneGroupService.findByGroupName(groupName);
    }
}
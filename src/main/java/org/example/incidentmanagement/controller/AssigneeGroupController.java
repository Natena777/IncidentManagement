package org.example.incidentmanagement.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.AssigneGroupService;
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
    public AssigneeGroupResponseDto createAssigneGroup(@RequestBody CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto){
        AssigneeGroupResponseDto result = assigneGroupService.createAssigneeGroup(createAssigneeGroupRequestDto);
        return result;
    }

    @DeleteMapping("/{id}")
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
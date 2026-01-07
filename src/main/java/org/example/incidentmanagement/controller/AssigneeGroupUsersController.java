package org.example.incidentmanagement.controller;

import org.example.incidentmanagement.dto.createResponse.CrAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.service.interfaces.AssigneeGroupUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.dto.createRequest.CrAssigneeGroupUsersRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;


@RestController
@RequestMapping("/api/assigneeGroupusers")
@Tag(name = "Assignee Group  Users Management API",
        description = "Manage Assignee Group Users In System")
public class AssigneeGroupUsersController {

    private final AssigneeGroupUserService assigneeGroupUserService;

    public AssigneeGroupUsersController(AssigneeGroupUserService assigneeGroupUserService) {
        this.assigneeGroupUserService = assigneeGroupUserService;
    }

    @PostMapping("/addUsers")
    @Operation(summary = "add Users to Assignee Group")
    public CrAssigneeGroupUsersResponseDto createAssigneeGroup(@RequestBody CrAssigneeGroupUsersRequestDto crAssigneeGroupUsersRequestDto){
        CrAssigneeGroupUsersResponseDto result = assigneeGroupUserService.addUserInAssigneeGroup(crAssigneeGroupUsersRequestDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove User From Assignee Group By ID")
    public ResponseEntity<ApiResponse> deleteAssigneeGroupUsers(@PathVariable Integer id) {
        assigneeGroupUserService.removeUserFromAssigneeGroup(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getCode(),
                ResponseCodes.ASSIGNEE_GROUP_DELETED.getMessage());

        return ResponseEntity.ok(apiResponse);
    }
    


    @GetMapping("/{id}")
    @Operation(summary = "Find Assignee Group Users By ID")
    public AssigneeGroupUsersResponseDto findById(@PathVariable Integer id) {
        return assigneeGroupUserService.findById(id);
    }  
    
}

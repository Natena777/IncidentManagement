package org.example.incidentmanagement.controller;

import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.example.incidentmanagement.service.AssigneeGroupUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateAssigneeGroupRequestDto;
import org.example.incidentmanagement.dto.response.AssigneeGroupUsersResponseDto;


@RestController
@RequestMapping("/api/assigneeGroupusers")
@Tag(name = "Assignee Group  Users Management API",
        description = "Manage Assignee Group Users In System")
public class AssigneeGroupUsersController {

    private final AssigneeGroupUsersService assigneeGroupUsersService;

    public AssigneeGroupUsersController(AssigneeGroupUsersService assigneeGroupUsersService) {
        this.assigneeGroupUsersService = assigneeGroupUsersService;
    }

    @PostMapping("/addUsers")
    @Operation(summary = "add Users to Assignee Group")
    public CreateAssigneeGroupUsersResponseDto createAssigneeGroup(@RequestBody CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto){
        CreateAssigneeGroupUsersResponseDto result = assigneeGroupUsersService.addUserInAssigneeGroup(createAssigneeGroupRequestDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove User From Assignee Group By ID")
    public ResponseEntity<ApiResponse> deleteAssigneeGroupUsers(@PathVariable Integer id) {
        assigneeGroupUsersService.removeUserFromAssigneeGroup(id);
        return ResponseEntity.ok(new ApiResponse("Assignee Group Users deleted successfully"));
    }


    @GetMapping("/id/{id}")
    @Operation(summary = "Find Assignee Group Users By ID")
    public AssigneeGroupUsersResponse findById(@PathVariable Integer id) {
        return assigneeGroupUsersService.findById(id);
    }  
    
}
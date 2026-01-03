package org.example.incidentmanagement.controller;

import org.example.incidentmanagement.dto.response.CreateAssigneeGroupUsersResponseDto;
import org.example.incidentmanagement.entity.AssigneeGroupUsers;

@RestController
@RequestMapping("/api/assigneeGroupusers")
@Tag(name = "Assignee Group  Users Management API",
        description = "Manage Assignee Group Users In System")
public class AssigneeGroupUsersController {

    private final AssigneeGroupUsers assigneeGroupUsers;

    public AssigneeGroupUsersController(AssigneeGroupUsers assigneeGroupUsers) {
        this.assigneeGroupUsers = assigneeGroupUsers;
    }

    @PostMapping("/addUsers")
    @Operation(summary = "add Users to Assignee Group")
    public CreateAssigneeGroupUsersResponseDto createAssigneeGroup(@RequestBody CreateAssigneeGroupRequestDto createAssigneeGroupRequestDto){
        CreateAssigneeGroupUsersResponseDto result = assigneeGroupUsers.createAssigneeGroup(createAssigneeGroupRequestDto);
        return result;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove User From Assignee Group By ID")
    public ResponseEntity<ApiResponse> deleteAssigneeGroupUsers(@PathVariable Integer id) {
        assigneeGroupUsers.deleteAssigneeGroupUsers(id);
        return ResponseEntity.ok(new ApiResponse("Assignee Group Users deleted successfully"));
    }


    @GetMapping("/id/{id}")
    @Operation(summary = "Find Assignee Group Users By ID")
    public AssigneeGroupUsersResponse findById(@PathVariable Integer id) {
        return assigneeGroupUsers.findById(id);
    }  
    
}
package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CrGroupCaseStatRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrGroupCaseStatResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateGroupCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.GroupCaseStatRespDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.interfaces.GroupCaseStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/groupCaseStatus")
@Tag(name = "Assignee Group  Case Satatus Management API",
        description = "Manage Assignee Group Case Status In System")
public class GroupCaseStatusController {

    private final GroupCaseStatusService groupCaseStatusService;

    public GroupCaseStatusController(GroupCaseStatusService groupCaseStatusService) {
        this.groupCaseStatusService = groupCaseStatusService;
    }



    @PostMapping("/addStatus")
    @Operation(summary = "add Status to Assignee Group")
    public GroupCaseStatRespDto addGroupCaseStatus(@RequestBody CrGroupCaseStatRequestDto crGroupCaseStatRequestDto){
        GroupCaseStatRespDto result = groupCaseStatusService.addCaseStatusOnGroup(crGroupCaseStatRequestDto);
        return result;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update Case Status For Assignee Group")
    public ResponseEntity<GroupCaseStatRespDto> updateGroupCaseStatus(
            @PathVariable Integer id,
            @RequestBody UpdateGroupCaseStatusReqDto updateGroupCaseStatusReqDto){

        GroupCaseStatRespDto respons = groupCaseStatusService.updateGroupCaseStatus(id, updateGroupCaseStatusReqDto);
        return ResponseEntity.ok(respons);

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Remove Case Status From Assignee Group By ID")
    public ResponseEntity<ApiResponse> deleteAssigneeGroupCaseStatus(@PathVariable Integer id) {
        groupCaseStatusService.deleteCaseStatusOnGroup(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getCode(),
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/id/{id}")
    @Operation(summary = "Find Assignee Group Case Status By ID")
    public GroupCaseStatRespDto findById(@PathVariable Integer id) {
        return groupCaseStatusService.findById(id);
    }

}

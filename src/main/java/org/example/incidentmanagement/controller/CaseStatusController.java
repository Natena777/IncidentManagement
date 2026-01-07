package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CrCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.requests.UpdateCaseStatusReqDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrCaseStatusesResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.interfaces.CaseStatuseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
@Tag(name = "Case Status Management",
    description = "Endpoint For Manage Case statuses")
public class CaseStatusController {

    private final CaseStatuseService caseStatuseService;

    public CaseStatusController(CaseStatuseService caseStatuseService) {
        this.caseStatuseService = caseStatuseService;
    }

    @GetMapping
    @Operation(summary = "Find All Case Statuses")
    public List<CaseStatusesResponseDto> findAll() {
        return caseStatuseService.findAllStatuses();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Find Status By Name")
    public CaseStatusesResponseDto findByName(@PathVariable String name) {
        return caseStatuseService.findByStatus(name);
    }


    @PostMapping("/create")
    @Operation(summary = "Create New Status")
    public CrCaseStatusesResponseDto createCaseStatuses(@RequestBody CrCaseStatusesRequestDto crCaseStatusesRequestDto) {
        return caseStatuseService.createCaseStatuses(crCaseStatusesRequestDto);
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Update Case Status")
    public ResponseEntity<CaseStatusesResponseDto> updateCaseStatus(
            @PathVariable Integer id,
            @RequestBody UpdateCaseStatusReqDto updateDto) {
        CaseStatusesResponseDto response = caseStatuseService.updateCaseStatus(id, updateDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Status By ID")
    public ResponseEntity<ApiResponse> deleteCaseStatuses(@PathVariable Integer id) {

        caseStatuseService.deleteCaseStatuses(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getCode(),
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);

    }




}

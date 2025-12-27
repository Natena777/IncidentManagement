package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateCaseStatusesRequestDto;
import org.example.incidentmanagement.dto.response.CaseStatusesResponseDto;
import org.example.incidentmanagement.dto.response.CreateCaseStatusesResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.CaseStatuseService;
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
    public List<CaseStatusesResponseDto> findAll() {
        return caseStatuseService.findAllStatuses();
    }

    @GetMapping("/{name}")
    public CaseStatusesResponseDto findByName(@PathVariable String name) {
        return caseStatuseService.findByStatus(name);
    }


    @PostMapping("/create")
    public CreateCaseStatusesResponseDto createCaseStatuses(@RequestBody CreateCaseStatusesRequestDto createCaseStatusesRequestDto) {
        return caseStatuseService.createCaseStatuses(createCaseStatusesRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCaseStatuses(@PathVariable Integer id) {

        caseStatuseService.deleteCaseStatuses(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getCode(),
                ResponseCodes.CASE_STATUS_DELETED_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);

    }




}

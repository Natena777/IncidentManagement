package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/case")
@Tag(name = "Case Management API",
        description = "Case Management")
public class CaseController {

    private final CaseService caseService;

    public CaseController(final CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Case")
    public CreateCaseResponseDto createCase(CreateCaseRequestDto createCaseRequestDto) {
        return caseService.createCase(createCaseRequestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Case")
    public ResponseEntity<ApiResponse> deleteCase(@PathVariable Integer id) {
        caseService.deleteCase(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_CASE_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_CASE_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }


}

package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CrCaseCommentRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateCaseRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateCaseResponseDto;
import org.example.incidentmanagement.dto.response.CaseCommentRespDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.interfaces.CaseCommentService;
import org.example.incidentmanagement.service.interfaces.CaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/case")
@Tag(name = "Case Management API",
        description = "Case Management")
public class CaseController {

    private final CaseService caseService;
    private final CaseCommentService caseCommentService;

    public CaseController(CaseService caseService,
                          CaseCommentService caseCommentService) {
        this.caseService = caseService;
        this.caseCommentService = caseCommentService;
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


    @GetMapping("/{caseId}/accessRights")
    @Operation(summary = "Get Case Access Rights")
    public List<Map<String, Object>> getAccessRights(@PathVariable Integer caseId) {
        return caseService.getAllCaseAccessRights(caseId);

    }

    @DeleteMapping("/{caseId}/accessRights/{id}")
    @Operation(summary = "Delete Case Acess Rights")
    public ResponseEntity<ApiResponse> deleteAcessRights(@PathVariable Integer caseId,
                                                         @PathVariable Integer id) {
        caseService.deleteCaseAccessRights(caseId, id);
        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.ACCESS_RIGHTS_DELETED_SUCCESFULY.getCode(),
                ResponseCodes.ACCESS_RIGHTS_DELETED_SUCCESFULY.getMessage()
        );

        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/{caseId}/comments")
    @Operation(summary = "Add Case Comment")
    public ResponseEntity<CaseCommentRespDto> addCaseComment(@PathVariable Integer caseId,
                                                             @RequestBody CrCaseCommentRequestDto requestDto){

        CaseCommentRespDto result  = caseCommentService.addComment(caseId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }


    @GetMapping("/{caseId}/comments")
    @Operation(summary = "Get Case All Comments")
    public ResponseEntity<List<CaseCommentRespDto>> getAllCaseComments(@PathVariable Integer caseId) {
        List<CaseCommentRespDto>result  = caseCommentService.getCommentsByCaseId(caseId);

        return ResponseEntity.ok(result);
    }



}

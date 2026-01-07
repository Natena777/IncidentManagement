package org.example.incidentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.response.UserResponseDto;
import org.example.incidentmanagement.service.ExcelGeneratorService;
import org.example.incidentmanagement.service.interfaces.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/excel/")
@Tag(name = "Excel Generator Management API",
        description = "Generate And Export Excel In System")
public class ExportExcelController {

    private final ExcelGeneratorService excelGeneratorService;
    private final UserService userService;

    public ExportExcelController(ExcelGeneratorService excelGeneratorService, UserService userService) {
        this.excelGeneratorService = excelGeneratorService;
        this.userService = userService;

    }


    @GetMapping("/export/excel")
    @Operation(summary = "Export All Users to Excel")
    public ResponseEntity<byte[]> exportUsersToExcel() {

        try {
            List<UserResponseDto> users = userService.findAllUsers();

            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            byte[] excelData = excelGeneratorService.generateExcel(users, "Users");

            String filename = "users_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
                    ".xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

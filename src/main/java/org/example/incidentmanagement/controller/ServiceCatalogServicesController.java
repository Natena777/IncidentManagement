package org.example.incidentmanagement.controller;

import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.response.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicescatalog")
public class ServiceCatalogServicesController {


    private final ServiceCatalogServices serviceCatalogServices;

    public ServiceCatalogServicesController(ServiceCatalogServices serviceCatalogServices) {
        this.serviceCatalogServices = serviceCatalogServices;
    }

    @GetMapping("/department")
    public List<ScDepartmentsResponseDto> getAllDepartments() {
        return serviceCatalogServices.findAllScDepartments();
    }

    @GetMapping("/department/{id}")
    public ScDepartmentsResponseDto findDepartmentById(Integer departmentID){
        return serviceCatalogServices.findScDepartmentById(departmentID);
    }


    @GetMapping("/department/name/{name}")
    public ScDepartmentsResponseDto findDepartmentByName(String departmentName){
        return serviceCatalogServices.findScDepartmentByName(departmentName);
    }

    @PostMapping("/department")
    public CreateScDepartmentsResponseDto createDepartment(@RequestBody CreateScDepartmentsRequestDto createScDepartmentsRequestDto){
        return serviceCatalogServices.createScDepartments(createScDepartmentsRequestDto);
    }

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        serviceCatalogServices.deleteScDepartments(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY.getMessage()
                );

        return ResponseEntity.ok(apiResponse);

    }








}

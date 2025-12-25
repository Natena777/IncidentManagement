package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.response.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.response.ScCategoryResponseDto;
import org.example.incidentmanagement.dto.response.ScDepartmentsResponseDto;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicescatalog")
@Tag(name = "Service Catalog Management API",
        description = "Service Department, Service Category, Service Subcategory and Service Item Endpoints")
public class ServiceCatalogServicesController {


    private final ServiceCatalogServices serviceCatalogServices;


    public ServiceCatalogServicesController(ServiceCatalogServices serviceCatalogServices) {
        this.serviceCatalogServices = serviceCatalogServices;
    }

    @GetMapping("/scDepartment")
    public List<ScDepartmentsResponseDto> getAllScDepartments() {
        return serviceCatalogServices.findAllScDepartments();
    }

    @GetMapping("/scDepartment/{id}")
    public ScDepartmentsResponseDto findScDepartmentById(@PathVariable Integer id){
        return serviceCatalogServices.findScDepartmentById(id);
    }


    @GetMapping("/scDepartment/name/{name}")
    public ScDepartmentsResponseDto findScDepartmentByName(@PathVariable String name){
        return serviceCatalogServices.findScDepartmentByName(name);
    }

    @PostMapping("/scDepartment")
    public CreateScDepartmentsResponseDto createScDepartment(@RequestBody CreateScDepartmentsRequestDto createScDepartmentsRequestDto){
        return serviceCatalogServices.createScDepartments(createScDepartmentsRequestDto);
    }

    @DeleteMapping("/scDepartment/delete/{id}")
    public ResponseEntity<ApiResponse> deleteScDepartment(@PathVariable Integer id){
        serviceCatalogServices.deleteScDepartments(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_DEPARTMENT_SUCCESFULY.getMessage()
                );

        return ResponseEntity.ok(apiResponse);

    }


    //Service Catalog Category Controller
    @GetMapping("/scCategory")
    public List<ScCategoryResponseDto> getAllScCategory(){
        return serviceCatalogServices.findAllScCategories();
    }

    @GetMapping("/scCategory/{id}")
    public ScCategoryResponseDto getScCategoryById(@PathVariable Integer id){
        return serviceCatalogServices.findScCategoryById(id);
    }

    @GetMapping("/scCategory/name/{name}")
    public ScCategoryResponseDto getScCategoryByName(@PathVariable String name){
        return serviceCatalogServices.findScCategoryByName(name);
    }

    @PostMapping("/scCategory")
    public CreateScCategoryResponseDto createScCategory(@RequestBody CreateScCategoryRequestDto createScCategoryRequestDto){
        return serviceCatalogServices.createScCategory(createScCategoryRequestDto);
    }

    @DeleteMapping("/scCategory/delete/{id}")
    public ResponseEntity<ApiResponse> deleteScCategory(@PathVariable Integer id){
        serviceCatalogServices.deleteScCategory(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY.getMessage()
        );

        return ResponseEntity.ok(apiResponse);

    }








}

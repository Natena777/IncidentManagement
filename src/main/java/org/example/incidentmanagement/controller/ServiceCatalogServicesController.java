package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CreateScCategoryResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScServicesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CreateScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.response.*;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicesCatalog")
public class ServiceCatalogServicesController {


    private final ServiceCatalogServices serviceCatalogServices;


    public ServiceCatalogServicesController(ServiceCatalogServices serviceCatalogServices) {
        this.serviceCatalogServices = serviceCatalogServices;
    }

    @GetMapping("/scFullFlow")
    @Operation(summary = "Get Service Catalog Full Flow",
                tags = "Service Catalog - Full Flow")
    public List<ServiceCatalogFullResponseDto> getServiceCatalogFullFlow(){
        return serviceCatalogServices.getScFullFlow();
    }

    @GetMapping("/scDepartment")
    @Operation(summary = "Get All Service Catalog Directions",
                tags = "Service Catalog - Department")
    public List<ScDepartmentsResponseDto> getAllScDepartments() {
        return serviceCatalogServices.findAllScDepartments();
    }

    @GetMapping("/scDepartment/{id}")
    @Operation(summary = "Get Service Catalog Direction By ID",
                tags = "Service Catalog - Department")
    public ScDepartmentsResponseDto findScDepartmentById(@PathVariable Integer id){
        return serviceCatalogServices.findScDepartmentById(id);
    }


    @GetMapping("/scDepartment/name/{name}")
    @Operation(summary = "Get Service Catalog Direction By Name",
                tags = "Service Catalog - Department")
    public ScDepartmentsResponseDto findScDepartmentByName(@PathVariable String name){
        return serviceCatalogServices.findScDepartmentByName(name);
    }

    @PostMapping("/scDepartment")
    @Operation(summary = "Create New Service Catalog Direction",
                tags = "Service Catalog - Department")
    public CreateScDepartmentsResponseDto createScDepartment(@RequestBody CreateScDepartmentsRequestDto createScDepartmentsRequestDto){
        return serviceCatalogServices.createScDepartments(createScDepartmentsRequestDto);
    }

    @DeleteMapping("/scDepartment/delete/{id}")
    @Operation(summary = "Delete Service Catalog Direction By ID",
                tags = "Service Catalog - Department")
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
    @Operation(summary = "Get All Service Catalog Categories",
                tags = "Service Catalog - Category")
    public List<ScCategoryResponseDto> getAllScCategory(){
        return serviceCatalogServices.findAllScCategories();
    }

    @GetMapping("/scCategory/{id}")
    @Operation(summary = "Get Service Catalog Category By ID",
                tags = "Service Catalog - Category")
    public ScCategoryResponseDto getScCategoryById(@PathVariable Integer id){
        return serviceCatalogServices.findScCategoryById(id);
    }

    @GetMapping("/scCategory/name/{name}")
    @Operation(summary = "Get Service Catalog Category By Name",
                tags = "Service Catalog - Category")
    public ScCategoryResponseDto getScCategoryByName(@PathVariable String name){
        return serviceCatalogServices.findScCategoryByName(name);
    }

    @PostMapping("/scCategory")
    @Operation(summary = "Create New Service Catalog Category",
                tags = "Service Catalog - Category")
    public CreateScCategoryResponseDto createScCategory(@RequestBody CreateScCategoryRequestDto createScCategoryRequestDto){
        return serviceCatalogServices.createScCategory(createScCategoryRequestDto);
    }

    @DeleteMapping("/scCategory/delete/{id}")
    @Operation(summary = "Delete Service Catalog Category",
                tags = "Service Catalog - Category")
    public ResponseEntity<ApiResponse> deleteScCategory(@PathVariable Integer id){
        serviceCatalogServices.deleteScCategory(id);

        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_CATEGORY_SUCCESFULY.getMessage()
        );

        return ResponseEntity.ok(apiResponse);
    }


    //Service Catalog SubCategory Controller
    @GetMapping("/scSubCategory")
    @Operation(summary = "Get All Service Catalog SubCategories",
                tags = "Service Catalog - SubCategory")
    public List<ScSubCategoryResponseDto> getAllScSubCategory(){
        return serviceCatalogServices.findAllScSubCategories();
    }

    @GetMapping("/scSubCategory/{id}")
    @Operation(summary = "Get Service Catalog SubCategory By ID",
                tags = "Service Catalog - SubCategory")
    public ScSubCategoryResponseDto getScSubCategoryById(@PathVariable Integer id){
        return serviceCatalogServices.findScSubCategoryById(id);
    }

    @GetMapping("/scSubCategory/name/{name}")
    @Operation(summary = "Get Service Catalog SubCategory By Name",
                tags = "Service Catalog - SubCategory")
    public ScSubCategoryResponseDto getScSubCategoryByName(@PathVariable String name){
        return serviceCatalogServices.findScSubCategoryByName(name);
    }

    @PostMapping("/scSubCategory")
    @Operation(summary = "Create New Service Catalog SubCategory",
                tags = "Service Catalog - SubCategory")
    public CreateScSubCategoryResponseDto createScSubCategory(@RequestBody CreateScSubCategoryRequestDto createScSubCategoryRequestDto){
        return serviceCatalogServices.createScSubCategory(createScSubCategoryRequestDto);
    }

    @DeleteMapping("/scSubCategory/delete/{id}")
    @Operation(summary = "Delete Service Catalog SubCategory By ID",
                tags = "Service Catalog - SubCategory")
    public ResponseEntity<ApiResponse> deleteScSubCategory(@PathVariable Integer id){
        serviceCatalogServices.deleteScSubCategory(id);
        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }



    //Service Catalog Services Controller
    @GetMapping("/scServices")
    @Operation(summary = "Get All Service Catalog Service Item",
                tags = "Service Catalog - Service Items")
    public List<ScServicesResponseDto> getAllScServices(){
        return serviceCatalogServices.findAllScServices();
    }

    @GetMapping("/scServices/{id}")
    @Operation(summary = "Get Service Catalog Service Item By ID",
                tags = "Service Catalog - Service Items")
    public ScServicesResponseDto getScServicesById(@PathVariable Integer id){
        return serviceCatalogServices.findScServicesById(id);
    }

    @GetMapping("/scServices/name/{name}")
    @Operation(summary = "Get Service Catalog Service Item By Name",
                tags = "Service Catalog - Service Items")
    public ScServicesResponseDto getScServicesByName(@PathVariable String name){
        return serviceCatalogServices.findScServicesByName(name);
    }

    @PostMapping("/scServices")
    @Operation(summary = "Create New Service Catalog Service Item",
                tags = "Service Catalog - Service Items")
    public CreateScServicesResponseDto createScServices(@RequestBody CreateScServicesRequestDto createScServicesRequestDto){
        return serviceCatalogServices.createScServices(createScServicesRequestDto);
    }

    @DeleteMapping("/scServices/delete/{id}")
    @Operation(summary = "Delete Service Catalog Service Item By ID",
                tags = "Service Catalog - Service Items")
    public ResponseEntity<ApiResponse> deleteScServices(@PathVariable Integer id){
        serviceCatalogServices.deleteScServices(id);
        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }




}

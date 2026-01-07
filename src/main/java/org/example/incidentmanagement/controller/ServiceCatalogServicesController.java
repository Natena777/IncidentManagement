package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.createRequest.CrScCategoryRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.createRequest.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.createRequest.CrScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.createResponse.CrScCategoryResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScDepartmentsResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScServicesResponseDto;
import org.example.incidentmanagement.dto.createResponse.CrScSubCategoryResponseDto;
import org.example.incidentmanagement.dto.requests.UpdateScCategoryReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScDepartmentsReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScServiceReqDto;
import org.example.incidentmanagement.dto.requests.UpdateScSubCategoryReqDto;
import org.example.incidentmanagement.dto.response.*;
import org.example.incidentmanagement.entity.ScSubCategory;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.interfaces.ServiceCatalogServices;
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
    public CrScDepartmentsResponseDto createScDepartment(@RequestBody CrScDepartmentsRequestDto crScDepartmentsRequestDto){
        return serviceCatalogServices.createScDepartments(crScDepartmentsRequestDto);
    }


    @PatchMapping("scDepartment/{id}")
    @Operation(summary = "Update Service Catalog Department",
            tags = "Service Catalog - Department")
    public ResponseEntity<ScDepartmentsResponseDto> updateScDepartment(
            @PathVariable Integer id,
            @RequestBody UpdateScDepartmentsReqDto updateScDepartmentsReqDto){
        ScDepartmentsResponseDto response = serviceCatalogServices.updateScDepartments(id, updateScDepartmentsReqDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/scDepartment/{id}")
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
    public CrScCategoryResponseDto createScCategory(@RequestBody CrScCategoryRequestDto crScCategoryRequestDto){
        return serviceCatalogServices.createScCategory(crScCategoryRequestDto);
    }

    @PatchMapping("/scCategory/{id}")
    @Operation(summary = "Update Service Catalog Category",
            tags = "Service Catalog - Category")
    public ResponseEntity<ScCategoryResponseDto> updateScCategory(
            @PathVariable Integer id,
            @RequestBody UpdateScCategoryReqDto updateScCategoryReqDto){
        ScCategoryResponseDto response = serviceCatalogServices.updateScCategory(id, updateScCategoryReqDto);
        return ResponseEntity.ok(response);
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
    public CrScSubCategoryResponseDto createScSubCategory(@RequestBody CrScSubCategoryRequestDto crScSubCategoryRequestDto){
        return serviceCatalogServices.createScSubCategory(crScSubCategoryRequestDto);
    }


    @PatchMapping("/scSubCategory/{id}")
    @Operation(summary = "Update  Service Catalog SubCategory",
            tags = "Service Catalog - SubCategory")
    public ResponseEntity<ScSubCategoryResponseDto> updateScCategory(
            @PathVariable Integer id,
            @RequestBody UpdateScSubCategoryReqDto updateScSubCategoryReqDto){
        ScSubCategoryResponseDto response = serviceCatalogServices.updateScSubCategory(id, updateScSubCategoryReqDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/scSubCategory/{id}")
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
    public CrScServicesResponseDto createScServices(@RequestBody CreateScServicesRequestDto createScServicesRequestDto){
        return serviceCatalogServices.createScServices(createScServicesRequestDto);
    }


    @PatchMapping("/scServices/{id}")
    @Operation(summary = "Update  Service Catalog Service Item",
            tags = "Service Catalog - Service Items")
    public ResponseEntity<ScServicesResponseDto> updateScServices(
            @PathVariable Integer id,
            @RequestBody UpdateScServiceReqDto updateScServiceReqDto){
        ScServicesResponseDto response = serviceCatalogServices.updateScServices(id, updateScServiceReqDto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/scServices/{id}")
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

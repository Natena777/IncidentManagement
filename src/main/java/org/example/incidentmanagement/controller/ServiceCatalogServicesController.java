package org.example.incidentmanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.incidentmanagement.dto.ApiResponse;
import org.example.incidentmanagement.dto.requests.CreateScCategoryRequestDto;
import org.example.incidentmanagement.dto.requests.CreateScDepartmentsRequestDto;
import org.example.incidentmanagement.dto.requests.CreateScServicesRequestDto;
import org.example.incidentmanagement.dto.requests.CreateScSubCategoryRequestDto;
import org.example.incidentmanagement.dto.response.*;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.service.ServiceCatalogServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    //Service Catalog SubCategory Controller
    @GetMapping("/scSubCategory")
    public List<ScSubCategoryResponseDto> getAllScSubCategory(){
        return serviceCatalogServices.findAllScSubCategories();
    }

    @GetMapping("/scSubCategory/{id}")
    public ScSubCategoryResponseDto getScSubCategoryById(@PathVariable Integer id){
        return serviceCatalogServices.findScSubCategoryById(id);
    }

    @GetMapping("/scSubCategory/name/{name}")
    public ScSubCategoryResponseDto getScSubCategoryByName(@PathVariable String name){
        return serviceCatalogServices.findScSubCategoryByName(name);
    }

    @PostMapping("/scSubCategory")
    public CreateScSubCategoryResponseDto createScSubCategory(@RequestBody CreateScSubCategoryRequestDto createScSubCategoryRequestDto){
        return serviceCatalogServices.createScSubCategory(createScSubCategoryRequestDto);
    }

    @DeleteMapping("/scSubCategory/delete/{id}")
    public ResponseEntity<ApiResponse> deleteScSubCategory(@PathVariable Integer id){
        serviceCatalogServices.deleteScSubCategory(id);
        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }



    //Service Catalog Services Controller
    @GetMapping("/scServices")
    public List<ScServicesResponseDto> getAllScServices(){
        return serviceCatalogServices.findAllScServices();
    }

    @GetMapping("/scServices/{id}")
    public ScServicesResponseDto getScServicesById(@PathVariable Integer id){
        return serviceCatalogServices.findScServicesById(id);
    }

    @GetMapping("/scServices/name/{name}")
    public ScServicesResponseDto getScServicesByName(@PathVariable String name){
        return serviceCatalogServices.findScServicesByName(name);
    }

    @PostMapping("/scServices")
    public CreateScServicesResponseDto createScServices(@RequestBody CreateScServicesRequestDto createScServicesRequestDto){
        return serviceCatalogServices.createScServices(createScServicesRequestDto);
    }

    @DeleteMapping("/scServices/delete/{id}")
    public ResponseEntity<ApiResponse> deleteScServices(@PathVariable Integer id){
        serviceCatalogServices.deleteScServices(id);
        ApiResponse apiResponse = new ApiResponse(
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getCode(),
                ResponseCodes.DELETE_SERVICE_CATALOG_SUBCATEGORY_SUCCESFULY.getMessage());

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/scFullFlow")
    public List<ServiceCatalogFullResponseDto> getServiceCatalogFullFlow(){
        return serviceCatalogServices.getScFullFlow();
    }





}

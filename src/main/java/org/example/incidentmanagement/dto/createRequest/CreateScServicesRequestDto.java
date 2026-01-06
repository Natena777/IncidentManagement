package org.example.incidentmanagement.dto.createRequest;


public class CreateScServicesRequestDto {

    private String servicesName;
    private String description;
    private Integer scDepartmentId;
    private Integer scCategoryId;
    private Integer scSubCategoryId;
    private String status;
    private String serviceType;
    private Integer assigneeGroupId;
    private String responseTimeType;
    private Integer responseTimeValue;
    private String resolutionTimeType;
    private Integer resolutionValue;

    public CreateScServicesRequestDto() {}

    public String getServicesName() {
        return servicesName;
    }
    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getScDepartmentId() {
        return scDepartmentId;
    }
    public void setScDepartmentId(Integer scDepartmentId) {
        this.scDepartmentId = scDepartmentId;
    }
    public Integer getScCategoryId() {
        return scCategoryId;
    }
    public void setScCategoryId(Integer scCategoryId) {
        this.scCategoryId = scCategoryId;
    }
    public Integer getScSubCategoryId() {
        return scSubCategoryId;
    }
    public void setScSubCategoryId(Integer scSubCategoryId) {
        this.scSubCategoryId = scSubCategoryId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public Integer getAssigneeGroupId() {
        return assigneeGroupId;
    }
    public void setAssigneeGroupId(Integer assigneeGroupId) {
        this.assigneeGroupId = assigneeGroupId;
    }
    public String getResponseTimeType() {
        return responseTimeType;
    }
    public void setResponseTimeType(String responseTimeType) {
        this.responseTimeType = responseTimeType;
    }
    public Integer getResponseTimeValue() {
        return responseTimeValue;
    }
    public void setResponseTimeValue(Integer responseTimeValue) {
        this.responseTimeValue = responseTimeValue;
    }
    public String getResolutionTimeType() {
        return resolutionTimeType;
    }
    public void setResolutionTimeType(String resolutionTimeType) {
        this.resolutionTimeType = resolutionTimeType;
    }
    public Integer getResolutionValue() {
        return resolutionValue;
    }
    public void setResolutionValue(Integer resolutionValue) {
        this.resolutionValue = resolutionValue;
    }



}

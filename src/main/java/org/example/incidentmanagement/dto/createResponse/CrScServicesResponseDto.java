package org.example.incidentmanagement.dto.createResponse;


public class CrScServicesResponseDto {

    private Integer serviceId;
    private String servicesName;
    private String description;
    private String scDepartmentName;
    private String scCategoryName;
    private String scSubCategoryName;
    private String status;
    private String serviceType;
    private String assigneeGroupName;
    private String responseTime;
    private String resolutionTime;


    public CrScServicesResponseDto() {}

    public CrScServicesResponseDto(Integer serviceId, String servicesName, String description,
                                   String scDepartmentName, String scCategoryName, String scSubCategoryName,
                                   String status, String serviceType, String assigneeGroupName,
                                   String responseTime, String resolutionTime) {
        this.serviceId = serviceId;
        this.servicesName = servicesName;
        this.description = description;
        this.scDepartmentName = scDepartmentName;
        this.scCategoryName = scCategoryName;
        this.scSubCategoryName = scSubCategoryName;
        this.status = status;
        this.serviceType = serviceType;
        this.assigneeGroupName = assigneeGroupName;
        this.responseTime = responseTime;
        this.resolutionTime = resolutionTime;

    }

    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

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

    public String getScDepartmentName() {
        return scDepartmentName;
    }

    public void setScDepartmentName(String scDepartmentName) {
        this.scDepartmentName = scDepartmentName;
    }

    public String getScCategoryName() {
        return scCategoryName;
    }

    public void setScCategoryName(String scCategoryName) {
        this.scCategoryName = scCategoryName;
    }

    public String getScSubCategoryName() {
        return scSubCategoryName;
    }

    public void setScSubCategoryName(String scSubCategoryName) {
        this.scSubCategoryName = scSubCategoryName;
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

    public String getAssigneeGroupName() {
        return assigneeGroupName;
    }

    public void setAssigneeGroupName(String assigneeGroupName) {
        this.assigneeGroupName = assigneeGroupName;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResolutionTime() {
        return resolutionTime;
    }

    public void setResolutionTime(String resolutionTime) {
        this.resolutionTime = resolutionTime;
    }

}

package org.example.incidentmanagement.entity;

import jakarta.persistence.*;
import org.example.incidentmanagement.converter.RequestTimeUnitConverter;
import org.example.incidentmanagement.enums.RequestTimeUnitEnums;

import java.time.LocalDateTime;

@Entity
@Table(name = "scservice")
public class ScServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String servicesName;

    @Column(name = "description")
    private String description;

    @Column(name = "scdepartment_id")
    private Integer scDepartmentId;

    @Column(name = "sccategory_id")
    private Integer scCategoryId;

    @Column(name = "scsubcategory_id")
    private Integer scSubCategoryId;

    @Column(name = "status")
    private String status;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "assignee_group_id")
    private Integer assigneeGroupId;

    @Column(name = "response_time_type")
    @Convert(converter = RequestTimeUnitConverter.class)
    private RequestTimeUnitEnums responseTimeType;

    @Column(name = "response_time_value")
    private Integer responseTimeValue;

    @Column(name = "resolution_time_type")
    @Convert(converter = RequestTimeUnitConverter.class)
    private RequestTimeUnitEnums resolutionTimeType;

    @Column(name = "resolution_time_value")
    private Integer resolutionValue;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedDate;

    public ScServices() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public RequestTimeUnitEnums getResponseTimeType() {
        return responseTimeType;
    }

    public void setResponseTimeType(RequestTimeUnitEnums responseTimeType) {
        this.responseTimeType = responseTimeType;
    }

    public Integer getResponseTimeValue() {
        return responseTimeValue;
    }

    public void setResponseTimeValue(Integer responseTimeValue) {
        this.responseTimeValue = responseTimeValue;
    }

    public RequestTimeUnitEnums getResolutionTimeType() {
        return resolutionTimeType;
    }

    public void setResolutionTimeType(RequestTimeUnitEnums resolutionTimeType) {
        this.resolutionTimeType = resolutionTimeType;
    }

    public Integer getResolutionValue() {
        return resolutionValue;
    }

    public void setResolutionValue(Integer resolutionValue) {
        this.resolutionValue = resolutionValue;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}

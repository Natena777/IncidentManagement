package org.example.incidentmanagement.dto.createResponse;

import java.time.LocalDateTime;

public class CreateCaseResponseDto {

    private Integer id;
    private String createdBy;
    private LocalDateTime createdOn;
    private String caseStatus;
    private String subject;
    private String description;
    private String channel;
    private String scDepartmentName;
    private String scCategoryName;
    private String scSubCategoryName;
    private String scServiceName;
    private String assigneeGroupName;
    private LocalDateTime responseTimeCalculated;
    private LocalDateTime resolutionTimeCalculated;


    public CreateCaseResponseDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getScServiceName() {
        return scServiceName;
    }

    public void setScServiceName(String scServiceName) {
        this.scServiceName = scServiceName;
    }

    public String getAssigneeGroupName() {
        return assigneeGroupName;
    }

    public void setAssigneeGroupName(String assigneeGroupName) {
        this.assigneeGroupName = assigneeGroupName;
    }

    public LocalDateTime getResponseTimeCalculated() {
        return responseTimeCalculated;
    }

    public void setResponseTimeCalculated(LocalDateTime responseTimeCalculated) {
        this.responseTimeCalculated = responseTimeCalculated;
    }

    public LocalDateTime getResolutionTimeCalculated() {
        return resolutionTimeCalculated;
    }

    public void setResolutionTimeCalculated(LocalDateTime resolutionTimeCalculated) {
        this.resolutionTimeCalculated = resolutionTimeCalculated;
    }
}

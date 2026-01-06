package org.example.incidentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "status")
    private Integer caseStatus;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "channel")
    private String channel;

    @Column(name = "scdepartment_id")
    private Integer scDepartmentId;

    @Column(name = "sccategory_id")
    private Integer scCategoryId;

    @Column(name = "scsubcategory_id")
    private Integer scSubCategoryId;

    @Column(name = "scservice_id")
    private Integer scServiceId;

    @Column(name = "assignee_group_id")
    private Integer assigneeGroupId;

    @Column(name = "assignee_user_id")
    private Integer assigneeUserId;

    @Column(name = "response_time_calculated")
    private LocalDateTime responseTimeCalculated;

    @Column(name = "resolutio_time_calculated")
    private LocalDateTime resolutionTimeCalculated;

    @Column(name = "response_time_actual")
    private LocalDateTime responseTimeActual;

    @Column(name = "resolution_time_actual")
    private LocalDateTime resolutionTimeActual;

    public Cases() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Integer caseStatus) {
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

    public Integer getScServiceId() {
        return scServiceId;
    }

    public void setScServiceId(Integer scServiceId) {
        this.scServiceId = scServiceId;
    }

    public Integer getAssigneeGroupId() {
        return assigneeGroupId;
    }

    public void setAssigneeGroupId(Integer assigneeGroupId) {
        this.assigneeGroupId = assigneeGroupId;
    }

    public Integer getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(Integer assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
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

    public LocalDateTime getResponseTimeActual() {
        return responseTimeActual;
    }

    public void setResponseTimeActual(LocalDateTime responseTimeActual) {
        this.responseTimeActual = responseTimeActual;
    }

    public LocalDateTime getResolutionTimeActual() {
        return resolutionTimeActual;
    }

    public void setResolutionTimeActual(LocalDateTime resolutionTimeActual) {
        this.resolutionTimeActual = resolutionTimeActual;
    }
}

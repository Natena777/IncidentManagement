package org.example.incidentmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignee_group_statuses")
public class AssigneeGroupCaseStatus {
    
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "current_status_id")
    private Integer currentStatusId;
    
    @Column(name = "previous_status_id")
    private Integer previousStatusId;
    
    @Column(name = "next_status_id")
    private Integer nextStatusId;
    
    @Column(name = "assignee_group_id")
    private Integer assigneeGroupId;
    
    @Column(name = "active")
    private String active;
    
    @Column(name = "created_by")
    private Integer createdBy;
    
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @Column(name = "updated_by")
    private Integer updatedBy;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentStatusId() {
        return currentStatusId;
    }

    public void setCurrentStatusId(Integer currentStatusId) {
        this.currentStatusId = currentStatusId;
    }

    public Integer getPreviousStatusId() {
        return previousStatusId;
    }

    public void setPreviousStatusId(Integer previousStatusId) {
        this.previousStatusId = previousStatusId;
    }

    public Integer getNextStatusId() {
        return nextStatusId;
    }

    public void setNextStatusId(Integer nextStatusId) {
        this.nextStatusId = nextStatusId;
    }

    public Integer getAssigneeGroupId() {
        return assigneeGroupId;
    }

    public void setAssigneeGroupId(Integer assigneeGroupId) {
        this.assigneeGroupId = assigneeGroupId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
}

package org.example.incidentmanagement.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_status_change_permissions")
public class CaseStatChangePerm {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "assignee_group_statuses_id")
    private Integer assigneeGroupStatusesId;
    @Column(name = "active")
    private String active;

    @Column(name = "crated_by")
    private Integer createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "can_read")
    private Integer canRead;

    @Column(name = "can_edit")
    private Integer canEdit;

    @Column(name = "can_delete")
    private Integer canDelete;

    @Column(name = "can_change")
    private Integer canChange;

    public CaseStatChangePerm() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssigneeGroupStatusesId() {
        return assigneeGroupStatusesId;
    }

    public void setAssigneeGroupStatusesId(Integer assigneeGroupStatusesId) {
        this.assigneeGroupStatusesId = assigneeGroupStatusesId;
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

    public Integer getCanRead() {
        return canRead;
    }

    public void setCanRead(Integer canRead) {
        this.canRead = canRead;
    }

    public Integer getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Integer canEdit) {
        this.canEdit = canEdit;
    }

    public Integer getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Integer canDelete) {
        this.canDelete = canDelete;
    }

    public Integer getCanChange() {
        return canChange;
    }

    public void setCanChange(Integer canChange) {
        this.canChange = canChange;
    }
}

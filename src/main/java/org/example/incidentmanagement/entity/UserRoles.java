package org.example.incidentmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "sysUserRoles")
public class UserRoles {

    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int roleId;
    private String MainRole;
    private String Status;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;


    public UserRoles() {}
    public UserRoles(int id, int userId, int roleId, String MainRole, String Status,
                     LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn, String updatedBy) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.MainRole = MainRole;
        this.Status = Status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getMainRole() {
        return MainRole;
    }
    public void setMainRole(String mainRole) {
        this.MainRole = mainRole;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        this.Status = status;
    }
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


}

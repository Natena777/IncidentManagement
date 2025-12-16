package org.example.incidentmanagement.dto.response;

import java.time.LocalDateTime;

public class UserRoleResponseDto {


    private int id;
    private int roleId;
    private int userId;
    private String mainRole;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;


    public UserRoleResponseDto() {}

    public UserRoleResponseDto(int id, int roleId, int userId, String mainRole
    , LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
        this.mainRole = mainRole;
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
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getMainRole() {
        return mainRole;
    }
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
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

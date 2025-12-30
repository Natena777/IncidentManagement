package org.example.incidentmanagement.dto.response;

import java.time.LocalDateTime;

public class RoleResponseDto {

    private int id;
    private String role;
    private String description;
    private String status;
    private LocalDateTime createdOn;
    private String createdBy;

    public RoleResponseDto() {}

    public RoleResponseDto(String role, String description, String status, LocalDateTime createdOn, String createdBy) {
        this.role = role;
        this.description = description;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreatedon() {
        return createdOn;
    }
    public void setCreatedon(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}

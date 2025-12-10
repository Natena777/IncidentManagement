package org.example.incidentmanagement.dto;

import java.time.LocalDateTime;

public class RoleResponseDto {

    private String role;
    private String description;
    private String status;
    private LocalDateTime createdon;
    private String createdBy;

    public RoleResponseDto() {}

    public RoleResponseDto(String role, String description, String status, LocalDateTime createdon, String createdBy) {
        this.role = role;
        this.description = description;
        this.status = status;
        this.createdon = createdon;
        this.createdBy = createdBy;
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
        return createdon;
    }
    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}

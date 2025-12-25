package org.example.incidentmanagement.dto.response;

public class ScCategoryResponseDto {

    private String scCategoryName;
    private String description;
    private String scDepartment;
    private String active;
    private String createdBy;
    private String updatedBy;

    public ScCategoryResponseDto() {
    }

    public ScCategoryResponseDto(String scCategoryName, String description, String scDepartment, String active, String createdBy, String updatedBy) {
        this.scCategoryName = scCategoryName;
        this.description = description;
        this.scDepartment = scDepartment;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;

    }

    public String getScCategoryName() {
        return scCategoryName;
    }
    public void setScCategoryName(String scCategoryName) {
        this.scCategoryName = scCategoryName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getScDepartment() {
        return scDepartment;
    }
    public void setScDepartment(String scDepartment) {
        this.scDepartment = scDepartment;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }




}

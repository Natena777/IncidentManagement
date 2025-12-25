package org.example.incidentmanagement.dto.response;


public class ScSubCategoryResponseDto {


    private String scSubCategoryName;
    private String description;
    private String scCategoryName;
    private String active;
    private String createdBy;
    private String updatedBy;


    public ScSubCategoryResponseDto() {}
    public ScSubCategoryResponseDto(String scSubCategoryName, String description, String scCategoryName,
                                    String active, String createdBy, String updatedBy) {
        this.scSubCategoryName = scSubCategoryName;
        this.description = description;
        this.scCategoryName = scCategoryName;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public String getScSubCategoryName() {
        return scSubCategoryName;
    }
    public void setScSubCategoryName(String scSubCategoryName) {
        this.scSubCategoryName = scSubCategoryName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getScCategoryName() {
        return scCategoryName;
    }
    public void setScCategoryName(String scCategoryName) {
        this.scCategoryName = scCategoryName;
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

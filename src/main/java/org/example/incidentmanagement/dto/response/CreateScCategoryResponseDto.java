package org.example.incidentmanagement.dto.response;

public class CreateScCategoryResponseDto {

    private String scCategoryName;
    private String scDepartment;
    private String description;
    private String active;


    public CreateScCategoryResponseDto() {
    }

    public CreateScCategoryResponseDto(String scCategoryName, String scDepartment,
                                       String description, String active) {
        this.scCategoryName = scCategoryName;
        this.scDepartment = scDepartment;
        this.description = description;
        this.active = active;
    }

    public String getScCategoryName() {
        return scCategoryName;
    }
    public void setScCategoryName(String scCategoryName) {
        this.scCategoryName = scCategoryName;
    }
    public String getScDepartment() {
        return scDepartment;
    }
    public void setScDepartment(String scDepartment) {
        this.scDepartment = scDepartment;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }



}

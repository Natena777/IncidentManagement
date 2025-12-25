package org.example.incidentmanagement.dto.response;

public class CreateScSubCategoryResponseDto {

    private String scSubCategoryName;
    private String description;
    private String scCategoryName;
    private String active;


    public CreateScSubCategoryResponseDto() {}
    public CreateScSubCategoryResponseDto(String scSubCategoryName, String description,
                                          String scCategoryName, String active) {
        this.scSubCategoryName = scSubCategoryName;
        this.description = description;
        this.scCategoryName = scCategoryName;
        this.active = active;
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


}

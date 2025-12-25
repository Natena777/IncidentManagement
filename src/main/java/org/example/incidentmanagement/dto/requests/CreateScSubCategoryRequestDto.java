package org.example.incidentmanagement.dto.requests;

public class CreateScSubCategoryRequestDto {

    private String scSubCategoryName;
    private String description;
    private Integer scCategoryId;
    private String active;


    public CreateScSubCategoryRequestDto() {}
    public CreateScSubCategoryRequestDto(String scSubCategoryName, String description,
                                         Integer scCategoryId, String active) {
        this.scSubCategoryName = scSubCategoryName;
        this.description = description;
        this.scCategoryId = scCategoryId;
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
    public Integer getScCategoryId() {
        return scCategoryId;
    }
    public void setScCategoryId(Integer scCategoryId) {
        this.scCategoryId = scCategoryId;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }


}

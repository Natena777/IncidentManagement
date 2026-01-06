package org.example.incidentmanagement.dto.createRequest;

public class CreateScSubCategoryRequestDto {

    private String scSubCategoryName;
    private String description;
    private Integer scCategoryId;



    public CreateScSubCategoryRequestDto() {}
    public CreateScSubCategoryRequestDto(String scSubCategoryName, String description,
                                         Integer scCategoryId) {
        this.scSubCategoryName = scSubCategoryName;
        this.description = description;
        this.scCategoryId = scCategoryId;
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



}

package org.example.incidentmanagement.dto.requests;

public class UpdateScSubCategoryReqDto {

    private String scSubCategoryName;
    private String description;
    private Integer scCategoryId;
    private Boolean active;

    public UpdateScSubCategoryReqDto() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package org.example.incidentmanagement.dto.requests;

public class CreateScCategoryRequestDto {

    private String scCategoryName;
    private String description;
    private String active;

    public CreateScCategoryRequestDto() {
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}

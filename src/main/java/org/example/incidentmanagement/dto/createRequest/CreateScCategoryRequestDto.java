package org.example.incidentmanagement.dto.createRequest;

public class CreateScCategoryRequestDto {

    private String scCategoryName;
    private String description;
    private Integer scDepartmentId;

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

    public Integer getScDepartmentId() {
        return scDepartmentId;
    }
    public void setScDepartmentId(Integer scDepartmentId) {
        this.scDepartmentId = scDepartmentId;
    }

}

package org.example.incidentmanagement.dto.requests;

public class UpdateScCategoryReqDto {

    private String scCategoryName;
    private String description;
    private Integer scDepartmentId;
    private Boolean active;


    public UpdateScCategoryReqDto() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package org.example.incidentmanagement.dto.requests;

public class UpdateScDepartmentsReqDto {

    private String departmentName;
    private String description;
    private Boolean active;

    public UpdateScDepartmentsReqDto() {}

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


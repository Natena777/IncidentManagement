package org.example.incidentmanagement.dto.requests;

public class CreateScDepartmentsRequestDto {

    private String departmentName;
    private String description;
    private String active;

    public CreateScDepartmentsRequestDto() {}
    public CreateScDepartmentsRequestDto(String departmentName, String description, String active) {
        this.departmentName = departmentName;
        this.description = description;
        this.active = active;
    }

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
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }


}
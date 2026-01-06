package org.example.incidentmanagement.dto.createRequest;

public class CreateScDepartmentsRequestDto {

    private String departmentName;
    private String description;

    public CreateScDepartmentsRequestDto() {}
    public CreateScDepartmentsRequestDto(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;

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



}
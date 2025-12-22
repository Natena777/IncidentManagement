package org.example.incidentmanagement.dto.response;



public class ScDepartmentsResponseDto {


    private Integer id;
    private String departmentName;
    private String description;
    private String active;
    private String createdBy;
    private String updatedBy;

    public ScDepartmentsResponseDto() {}
    public ScDepartmentsResponseDto(Integer id, String departmentName, String description, String active, String createdBy, String updatedBy) {
        this.id = id;
        this.departmentName = departmentName;
        this.description = description;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }



}
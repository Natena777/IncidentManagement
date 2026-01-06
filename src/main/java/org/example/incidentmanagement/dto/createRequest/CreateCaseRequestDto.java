package org.example.incidentmanagement.dto.createRequest;


public class CreateCaseRequestDto {


    private String subject;
    private String description;
    private Integer scDepartmentId;
    private Integer scCategoryId;
    private Integer scSubCategoryId;
    private Integer scServiceId;

    public CreateCaseRequestDto() {}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Integer getScCategoryId() {
        return scCategoryId;
    }

    public void setScCategoryId(Integer scCategoryId) {
        this.scCategoryId = scCategoryId;
    }

    public Integer getScSubCategoryId() {
        return scSubCategoryId;
    }

    public void setScSubCategoryId(Integer scSubCategoryId) {
        this.scSubCategoryId = scSubCategoryId;
    }

    public Integer getScServiceId() {
        return scServiceId;
    }

    public void setScServiceId(Integer scServiceId) {
        this.scServiceId = scServiceId;
    }
}

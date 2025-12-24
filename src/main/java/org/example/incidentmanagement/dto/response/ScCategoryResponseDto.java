package org.example.incidentmanagement.dto.response;

public class ScCategoryResponseDto {

    private String ScCategoryName;
    private String description;
    private String active;
    private String CreatedBy;
    private String UpdatedBy;

    public ScCategoryResponseDto() {
    }

    public ScCategoryResponseDto(String scCategoryName, String description, String active, String createdBy, String updatedBy) {
        ScCategoryName = scCategoryName;
        this.description = description;
        this.active = active;
        CreatedBy = createdBy;
        UpdatedBy = updatedBy;
    }

    public String getScCategoryName() {
        return ScCategoryName;
    }
    public void setScCategoryName(String scCategoryName) {
        ScCategoryName = scCategoryName;
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
        return CreatedBy;
    }
    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
    public String getUpdatedBy() {
        return UpdatedBy;
    }

}

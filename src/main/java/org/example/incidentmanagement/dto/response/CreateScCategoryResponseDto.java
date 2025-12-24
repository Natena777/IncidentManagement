package org.example.incidentmanagement.dto.response;

public class CreateScCategoryResponseDto {

    private String ScCategoryName;
    private String description;
    private String active;


    public CreateScCategoryResponseDto() {
    }

    public CreateScCategoryResponseDto(String scCategoryName, String description, String active) {
        ScCategoryName = scCategoryName;
        this.description = description;
        this.active = active;
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



}

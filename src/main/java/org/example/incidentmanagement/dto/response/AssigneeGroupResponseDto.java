package org.example.incidentmanagement.dto.response;

public class AssigneeGroupResponseDto {

    private String groupName;
    private String groupDescription;
    private String active;
    private String createdBy;
    private String updatedBy;

    public AssigneeGroupResponseDto() {}
    public AssigneeGroupResponseDto(String groupName, String groupDescription, String active, String createdBy,
                                    String updatedBy) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupDescription() {
        return groupDescription;
    }
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
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

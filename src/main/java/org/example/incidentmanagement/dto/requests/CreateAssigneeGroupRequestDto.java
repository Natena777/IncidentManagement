package org.example.incidentmanagement.dto.requests;

public class CreateAssigneeGroupRequestDto {
    private String groupName;
    private String groupDescription;
    private String active;

    public CreateAssigneeGroupRequestDto() {

    }
    public CreateAssigneeGroupRequestDto(String groupName, String groupDescription, String active) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.active = active;

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

}

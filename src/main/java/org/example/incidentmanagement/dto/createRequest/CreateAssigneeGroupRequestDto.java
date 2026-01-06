package org.example.incidentmanagement.dto.createRequest;

public class CreateAssigneeGroupRequestDto {
    private String groupName;
    private String groupDescription;

    public CreateAssigneeGroupRequestDto() {

    }
    public CreateAssigneeGroupRequestDto(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;

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


}

package org.example.incidentmanagement.dto.requests;

public class UpdateAssigneeGroupReqDto {

    private String groupName;
    private String groupDescription;
    private Boolean active;

    public UpdateAssigneeGroupReqDto() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

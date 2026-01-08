package org.example.incidentmanagement.dto.createRequest;



public class CrAssigneeGroupUsersRequestDto {

    private Integer userId;
    private Integer assigneeGroupId;

    public CrAssigneeGroupUsersRequestDto() {}


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getAssigneeGroupId() {
        return assigneeGroupId;
    }

    public void setAssigneeGroupId(Integer assigneeGroupId) {
        this.assigneeGroupId = assigneeGroupId;
    }

}

package org.example.incidentmanagement.dto.createRequest;



public class CreateAssigneeGroupUsersRequestDto {

    private Integer id;
    private Integer userId;
    private Integer assigneeGroupId;

    public CreateAssigneeGroupUsersRequestDto() {}


    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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

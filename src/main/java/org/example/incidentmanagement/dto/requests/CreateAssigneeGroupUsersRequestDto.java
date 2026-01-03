package org.example.incidentmanagement.dto.requests;



public class CreateAssigneeGroupUsersRequestDto {

    private Integer id;
    private Integer userId;
    private Integer assigneeGroupId;
    private String active;


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
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

}

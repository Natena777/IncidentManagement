package org.example.incidentmanagement.dto.response;

public class CreateAssigneeGroupUsersResponseDto {

    private Integer id;
    private String user;
    private String assigneeGroup;
    private String active;

    public CreateAssigneeGroupUsersResponseDto() {}


    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getAssigneeGroupId(){
        return assigneeGroupId;
    }
    public void setAssigneeGroupId(String assigneeGroupId){
        this.assigneeGroupId = assigneeGroupId;
    }
    public String getActive(){
        return active;
    }
    public void setActive(String active){
        this.active = active;
    }
    
    
}

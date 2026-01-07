package org.example.incidentmanagement.dto.createResponse;

public class CrAssigneeGroupUsersResponseDto {

    private Integer id;
    private String user;
    private String assigneeGroup;
    private String active;

    public CrAssigneeGroupUsersResponseDto() {}


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAssigneeGroup() {
        return assigneeGroup;
    }

    public void setAssigneeGroup(String assigneeGroup) {
        this.assigneeGroup = assigneeGroup;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}

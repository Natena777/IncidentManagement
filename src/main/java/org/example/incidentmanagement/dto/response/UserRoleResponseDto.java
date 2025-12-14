package org.example.incidentmanagement.dto.response;

public class UserRoleResponseDto {


    private int id;
    private int roleID;
    private int userId;
    private String mainRole;



    public UserRoleResponseDto() {}

    public UserRoleResponseDto(int id, int roleID, int userId, String mainRole) {
        this.id = id;
        this.roleID = roleID;
        this.userId = userId;
        this.mainRole = mainRole;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getMainRole() {
        return mainRole;
    }
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }

}

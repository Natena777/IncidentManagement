package org.example.incidentmanagement.dto.requests;

public class CreateUserRoleRequestDto {

    private int roleID;
    private int userID;
    private String mainRole;

    public CreateUserRoleRequestDto() {}
    public CreateUserRoleRequestDto(int roleID, int userID, String mainRole) {
        this.roleID = roleID;
        this.userID = userID;
        this.mainRole = mainRole;
    }
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getMainRole() {
        return mainRole;
    }
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }


}

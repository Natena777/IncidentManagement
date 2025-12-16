package org.example.incidentmanagement.dto.requests;

public class CreateUserRoleRequestDto {

    private int roleId;
    private int userId;
    private String mainRole;

    public CreateUserRoleRequestDto() {}
    public CreateUserRoleRequestDto(int roleId, int userId, String mainRole) {
        this.roleId = roleId;
        this.userId = userId;
        this.mainRole = mainRole;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

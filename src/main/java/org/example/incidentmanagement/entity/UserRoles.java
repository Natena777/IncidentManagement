package org.example.incidentmanagement.entity;

public class UserRoles {

    private int id;
    private int userId;
    private int roleId;
    private String MainRole;
    private String Status;

    public UserRoles() {}
    public UserRoles(int id, int userId, int roleId, String MainRole, String Status) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.MainRole = MainRole;
        this.Status = Status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getMainRole() {
        return MainRole;
    }
    public void setMainRole(String mainRole) {
        this.MainRole = mainRole;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        this.Status = status;
    }

}

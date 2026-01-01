package org.example.incidentmanagement.dto.requests;

public class UpdateUserRoleRequestDto {

    private int userId;
    private int roleId;
    private Boolean mainRole;

    public UpdateUserRoleRequestDto() {}
    public UpdateUserRoleRequestDto(int userId, int roleId, Boolean mainRole) {
        this.userId = userId;
        this.roleId = roleId;
        this.mainRole = mainRole;
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

    public Boolean getMainRole(){
        return mainRole;
    }

    public void setMainRole(Boolean mainRole){
        this.mainRole = mainRole;
    }


}

package org.example.incidentmanagement.dto.createRequest;

import jakarta.validation.constraints.NotNull;


public class CrUserRoleRequestDto {

    @NotNull
    private Integer roleId;

    @NotNull
    private Integer userId;

    private Boolean mainRole;

    public CrUserRoleRequestDto() {}
    public CrUserRoleRequestDto(Integer roleId, Integer userId, Boolean mainRole) {
        this.roleId = roleId;
        this.userId = userId;
        this.mainRole = mainRole;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Boolean getMainRole() {
        return mainRole;
    }
    public void setMainRole(Boolean mainRole) {
        this.mainRole = mainRole;
    }

}
package org.example.incidentmanagement.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRoleRequestDto {

    @NotNull
    private Integer roleId;

    @NotNull
    private Integer userId;

    private Boolean mainRole;

    public CreateUserRoleRequestDto() {}
    public CreateUserRoleRequestDto(Integer roleId, Integer userId, Boolean mainRole) {
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
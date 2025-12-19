package org.example.incidentmanagement.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRoleRequestDto {

    @NotNull
    private Integer roleId;

    @NotNull
    private Integer userId;

    @NotNull
    @Size(max = 1)
    private String mainRole;

    public CreateUserRoleRequestDto() {}
    public CreateUserRoleRequestDto(Integer roleId, Integer userId, String mainRole) {
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
    public String getMainRole() {
        return mainRole;
    }
    public void setMainRole(String mainRole) {
        this.mainRole = mainRole;
    }


}

package org.example.incidentmanagement.dto;

public class RoleRequestDto {
    private String roleName;
    private String roleDescription;


    public RoleRequestDto() {}



    public RoleRequestDto(String roleName, String roleDescription, String roleStatus) {

        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescription() {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }


}

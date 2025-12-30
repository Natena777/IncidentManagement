package org.example.incidentmanagement.dto.requests;

public class RoleRequestDto {
    private String roleName;

    public RoleRequestDto() {}


    public RoleRequestDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}

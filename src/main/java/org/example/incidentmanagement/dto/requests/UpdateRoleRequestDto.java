package org.example.incidentmanagement.dto.requests;

public class UpdateRoleRequestDto {

    private String newRoleName;
    private String newRoleDescription;
    private String newRoleStatus;

    public UpdateRoleRequestDto() {}

    public UpdateRoleRequestDto(String newRoleName, String newRoleDescription, String newRoleStatus) {
        this.newRoleName = newRoleName;
        this.newRoleDescription = newRoleDescription;
        this.newRoleStatus = newRoleStatus;
    }

    public String getNewRoleName() {
        return newRoleName;
    }
    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }
    public String getNewRoleDescription() {
        return newRoleDescription;
    }
    public void setNewRoleDescription(String newRoleDescription) {
        this.newRoleDescription = newRoleDescription;
    }
    public String getNewRoleStatus() {
        return newRoleStatus;
    }
    public void setNewRoleStatus(String newRoleStatus) {
        this.newRoleStatus = newRoleStatus;
    }


}

package org.example.incidentmanagement.dto.requests;

public class UpdateGroupCaseStatusReqDto {

    private Boolean active;

    public UpdateGroupCaseStatusReqDto() {}

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

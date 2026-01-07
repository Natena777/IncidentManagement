package org.example.incidentmanagement.dto.requests;

public class UpdateCaseStatusReqDto {


    private String caseStatus;
    private String caseStatusDescription;
    private Boolean active;
    private Boolean isFinal;
    private Boolean isPaused;

    public UpdateCaseStatusReqDto() {}


    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatusDescription() {
        return caseStatusDescription;
    }

    public void setCaseStatusDescription(String caseStatusDescription) {
        this.caseStatusDescription = caseStatusDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean aFinal) {
        isFinal = aFinal;
    }

    public Boolean getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(Boolean paused) {
        isPaused = paused;
    }
}

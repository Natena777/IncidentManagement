package org.example.incidentmanagement.dto.response;


public class CreateCaseStatusesResponseDto {

    private String statusName;
    private String statusDescription;
    private String isFinal;
    private String isPaused;



    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public String getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(String isPaused) {
        this.isPaused = isPaused;
    }
}

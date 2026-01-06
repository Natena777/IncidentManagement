package org.example.incidentmanagement.dto.createRequest;



public class CrCasesStatChangePermRequestDto {


    private Integer assigneeGroupStatusesId;
    private boolean canRead;
    private boolean canEdit;
    private boolean canDelete;
    private boolean canChange;


    public CrCasesStatChangePermRequestDto() {}

    public Integer getAssigneeGroupStatusesId() {
        return assigneeGroupStatusesId;
    }

    public void setAssigneeGroupStatusesId(Integer assigneeGroupStatusesId) {
        this.assigneeGroupStatusesId = assigneeGroupStatusesId;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }
}

package org.example.incidentmanagement.dto.createRequest;


public class CrGroupCaseStatRequestDto {

    private Integer current_status_id;
    private Integer previous_status_id;
    private Integer next_status_id;
    private Integer assignee_group_id;

    public CrGroupCaseStatRequestDto() {}

    public Integer getCurrent_status_id() {
        return current_status_id;
    }

    public void setCurrent_status_id(Integer current_status_id) {
        this.current_status_id = current_status_id;
    }

    public Integer getPrevious_status_id() {
        return previous_status_id;
    }

    public void setPrevious_status_id(Integer previous_status_id) {
        this.previous_status_id = previous_status_id;
    }

    public Integer getNext_status_id() {
        return next_status_id;
    }

    public void setNext_status_id(Integer next_status_id) {
        this.next_status_id = next_status_id;
    }

    public Integer getAssignee_group_id() {
        return assignee_group_id;
    }

    public void setAssignee_group_id(Integer assignee_group_id) {
        this.assignee_group_id = assignee_group_id;
    }
}

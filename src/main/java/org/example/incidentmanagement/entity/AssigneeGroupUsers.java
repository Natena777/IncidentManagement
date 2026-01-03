package org.example.src.incidentmanagement.entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_assignee_groups_users")
public class AssigneeGroupUsers {

    private Integer id;
    private Integer userId;
    private Integer assigneeGroupId;
    private String active;
    private Integer createdBy;
    private LocalDateTime createdOn;
    private Integer updatedBy;
    private LocalDateTime updatedOn;

    

}
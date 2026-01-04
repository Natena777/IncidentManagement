package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.AssigneeGroupCaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCaseStatusRepository extends JpaRepository<AssigneeGroupCaseStatus, Integer> {
}

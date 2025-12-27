package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.CaseStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseStatusesRepository extends JpaRepository<CaseStatuses, Integer> {

    CaseStatuses findBystatusName(String statusName);

}

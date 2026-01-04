package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.CaseStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseStatusesRepository extends JpaRepository<CaseStatuses, Integer> {

    CaseStatuses findBystatusName(String statusName);

    @Query("select cs.statusName from CaseStatuses cs where cs.id = :id")
    String getNameById(Integer id);

}

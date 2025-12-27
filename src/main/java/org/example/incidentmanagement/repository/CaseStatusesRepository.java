package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.CaseStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseStatusesRepository extends JpaRepository<CaseStatuses, Integer> {

    CaseStatuses findByName(String statusName);

}

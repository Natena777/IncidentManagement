package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.CaseStatChangePerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseStatChangePermRepository  extends JpaRepository<CaseStatChangePerm, Integer> {
}

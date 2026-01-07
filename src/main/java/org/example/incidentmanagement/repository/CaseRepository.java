package org.example.incidentmanagement.repository;



import org.example.incidentmanagement.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Cases, Integer> {

}

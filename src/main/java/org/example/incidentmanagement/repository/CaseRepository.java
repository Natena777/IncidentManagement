package org.example.incidentmanagement.repository;



import org.example.incidentmanagement.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseRepository extends JpaRepository<Cases, Integer> {

    @Query("select case when count(c.id) > 0 then true else false end from Cases c where c.assigneeGroupId = :assigneeGroupId")
    boolean findAssigneeGroupCases (Integer assigneeGroupId);

    @Query("select c.number from Cases c where c.id = :id")
    String getCaseNumberById(Integer id);

}

package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.AssigneeGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeGroupRepository extends JpaRepository<AssigneeGroups, Integer> {


    @Query("select ag.name from AssigneeGroups ag where ag.id = :id")
    Optional<String> findGroupNameById(Integer id);
}

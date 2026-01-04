package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.AssigneeGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssigneeGroupRepository extends JpaRepository<AssigneeGroups, Integer> {

    Optional<AssigneeGroups> findByGroupName(String groupName);

    @Query("select ag.groupName from AssigneeGroups ag where ag.id = :id")
    Optional<String> findGroupNameById(Integer id);
}

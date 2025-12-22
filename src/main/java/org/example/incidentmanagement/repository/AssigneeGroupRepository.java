package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.AssigneeGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeGroupRepository extends JpaRepository<AssigneeGroups, Integer> {


    AssigneeGroups findByGroupName(String groupName);
}

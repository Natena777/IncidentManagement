package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeGroupUsersRepository extends JpaRepository<AssigneeGroupUsers, Integer> {

}

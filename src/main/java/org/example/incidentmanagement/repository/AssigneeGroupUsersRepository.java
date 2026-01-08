package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.AssigneeGroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AssigneeGroupUsersRepository extends JpaRepository<AssigneeGroupUsers, Integer> {

    boolean existsAssigneeGroupUsersByAssigneeGroupId(Integer assigneeGroupId);

    @Query("""
            select case when count(agu.id) > 0 then true else false end from AssigneeGroupUsers agu 
            where agu.assigneeGroupId = :assigneeGroupId 
            and agu.userId= :userId""")
    boolean existsAssigneeGroupUsersByAssigneeUserId(Integer userId, Integer assigneeGroupId);

}

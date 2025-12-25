package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.ScDepartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScDepartmentsRepository extends JpaRepository<ScDepartments, Integer> {

    Optional<ScDepartments> findByDepartmentName(String departmentName);

    @Query("select sd.departmentName from ScDepartments sd where sd.id = :departmentId")
    String findByDepartmenNametId(Integer departmentId);

}
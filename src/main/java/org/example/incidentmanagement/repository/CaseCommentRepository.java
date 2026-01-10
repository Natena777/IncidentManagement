package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.CaseComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseCommentRepository extends JpaRepository<CaseComments, Integer> {


    List<CaseComments> findByCaseId(Integer caseId);

}

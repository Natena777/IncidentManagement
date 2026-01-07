package org.example.incidentmanagement.repository;

import org.example.incidentmanagement.entity.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {


    Optional<WorkingHours> findByAssigneeGroupIdAndDayOfWeekAndActive(
            Integer assigneeGroupId,
            DayOfWeek dayOfWeek,
            String active
    );
}

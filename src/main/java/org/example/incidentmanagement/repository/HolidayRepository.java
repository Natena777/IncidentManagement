package org.example.incidentmanagement.repository;


import org.example.incidentmanagement.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {


    Optional<Holiday> findByAssigneeGroupIdAndHolidayDateAndActive(
            Integer assigneeGroupId,
            LocalDate holidayDate,
            String active
    );
}

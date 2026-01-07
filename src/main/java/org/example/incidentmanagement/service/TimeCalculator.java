package org.example.incidentmanagement.service;

import org.example.incidentmanagement.converter.DefaultConverter;
import org.example.incidentmanagement.entity.Holiday;
import org.example.incidentmanagement.entity.ScServices;
import org.example.incidentmanagement.entity.WorkingHours;
import org.example.incidentmanagement.enums.RequestTimeUnitEnums;
import org.example.incidentmanagement.enums.WokingHourTypeEnum;
import org.example.incidentmanagement.exceptions.CustomException;
import org.example.incidentmanagement.exceptions.ResponseCodes;
import org.example.incidentmanagement.repository.HolidayRepository;
import org.example.incidentmanagement.repository.ScServicesRepository;
import org.example.incidentmanagement.repository.WorkingHoursRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TimeCalculator {

    private static final Logger logger = LoggerFactory.getLogger(TimeCalculator.class);

    private final WorkingHoursRepository workingHoursRepository;
    private final HolidayRepository holidayRepository;
    private final ScServicesRepository scServicesRepository;
    private final DefaultConverter defaultConverter;

    public TimeCalculator(WorkingHoursRepository workingHoursRepository,
                          HolidayRepository holidayRepository,
                          ScServicesRepository scServicesRepository,
                          DefaultConverter defaultConverter) {
        this.workingHoursRepository = workingHoursRepository;
        this.holidayRepository = holidayRepository;
        this.scServicesRepository = scServicesRepository;
        this.defaultConverter = defaultConverter;
    }


    public LocalDateTime calculateResponseTimeByService(Integer serviceId,
                                                LocalDateTime createdOn,
                                                Integer assigneeGroupId) {
        logger.info("Calculating response time for serviceId={}, createdOn={}, groupId={}",
                serviceId, createdOn, assigneeGroupId);

        ScServices serviceItem = scServicesRepository.findById(serviceId)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));


        int responseMinutes = defaultConverter.convertTimeUnitToMinutes(
                serviceItem.getResponseTimeValue(),
                serviceItem.getResponseTimeType()
        );

        RequestTimeUnitEnums responseType = serviceItem.getResponseTimeType();

        if (responseType == RequestTimeUnitEnums.WORKING_MINUTES ||
                responseType == RequestTimeUnitEnums.WORKING_HOURS ||
                responseType == RequestTimeUnitEnums.WORKING_DAYS) {

            logger.info("Using working hours calculation with {} minutes", responseMinutes);
            LocalDateTime calculatedResponse = calculateWorkingHoursDeadline(
                    createdOn,
                    responseMinutes,
                    assigneeGroupId);

            return calculatedResponse;
        }

        logger.info("Using calendar time, adding {} minutes", responseMinutes);
        return createdOn.plusMinutes(responseMinutes);
    }


    public LocalDateTime calculateResolutionTimeByService(Integer serviceId,
                                                          LocalDateTime createdOn,
                                                          Integer assigneeGroupId){
        logger.info("Called Calculate Resolution Time By Service for ServiceID: {}, CreatedOn: {}, GroupID: {}",
                serviceId, createdOn, assigneeGroupId);

        ScServices serviceItem = scServicesRepository.findById(serviceId)
                .orElseThrow(() -> new CustomException(ResponseCodes.INVALID_SERIVCE_CATALOG_SERVICES));


        int resolutionMinutes = defaultConverter.convertTimeUnitToMinutes(
                serviceItem.getResolutionValue(),
                serviceItem.getResolutionTimeType()
        );

        RequestTimeUnitEnums resolutionType = serviceItem.getResolutionTimeType();

        if (resolutionType == RequestTimeUnitEnums.WORKING_MINUTES ||
                resolutionType == RequestTimeUnitEnums.WORKING_HOURS ||
                resolutionType == RequestTimeUnitEnums.WORKING_DAYS) {

            LocalDateTime calculatedResolution = calculateWorkingHoursDeadline(
                    createdOn,
                    resolutionMinutes,
                    assigneeGroupId);

            return calculatedResolution;
        }

        logger.info("Using calendar time, adding {} minutes", resolutionMinutes);
        return createdOn.plusMinutes(resolutionMinutes);

    }



    private LocalDateTime calculateWorkingHoursDeadline(LocalDateTime startTime,
                                                       int responseMinutes,
                                                       Integer assigneeGroupId) {
        logger.info("Calculating deadline: startTime={}, responseMinutes={}, groupId={}",
                startTime, responseMinutes, assigneeGroupId);

        LocalDateTime current = startTime;
        int remainingMinutes = responseMinutes;
        int iterations = 0;
        int maxIterations = 365;

        while (remainingMinutes > 0 && iterations < maxIterations) {
            iterations++;

            logger.debug("Iteration {}: current={}, remaining={} minutes",
                    iterations, current, remainingMinutes);

            WorkingHours workingHours = getCurrentDayWorkingHours(current, assigneeGroupId);

            if (workingHours == null) {
                logger.debug("Not a working day, moving to next working day");
                current = getNextWorkingDay(current, assigneeGroupId);
                continue;
            }

            LocalTime currentTime = current.toLocalTime();
            LocalTime workStart = workingHours.getStartTime();
            LocalTime workEnd = workingHours.getEndTime();

            if (currentTime.isBefore(workStart)) {
                logger.debug("Before work start {}, moving to work start", workStart);
                current = LocalDateTime.of(current.toLocalDate(), workStart);
                continue;
            }

            if (currentTime.isAfter(workEnd) || currentTime.equals(workEnd)) {
                logger.debug("After work end {}, moving to next working day", workEnd);
                current = getNextWorkingDay(current, assigneeGroupId);
                continue;
            }

            long minutesUntilEnd = Duration.between(currentTime, workEnd).toMinutes();

            logger.debug("Minutes until work end: {}, remaining needed: {}",
                    minutesUntilEnd, remainingMinutes);

            if (remainingMinutes <= minutesUntilEnd) {
                logger.debug("Remaining {} minutes fit in current day", remainingMinutes);
                current = current.plusMinutes(remainingMinutes);
                logger.info("Final deadline: {}", current);
                return current;
            } else {
                int minutesUsedToday = (int) minutesUntilEnd;
                remainingMinutes -= minutesUsedToday;

                logger.debug("Used {} minutes from current day, {} minutes remaining",
                        minutesUsedToday, remainingMinutes);

                current = getNextWorkingDay(current, assigneeGroupId);
            }
        }

        if (iterations >= maxIterations) {
            logger.error("Max iterations reached! Could not calculate deadline.");
            throw new RuntimeException("Failed to calculate deadline - too many iterations");
        }

        logger.info("Calculated deadline: {}", current);
        return current;
    }

    private WorkingHours getCurrentDayWorkingHours(LocalDateTime currentDateTime,
                                                   Integer assigneeGroupId) {
        LocalDate currentDate = currentDateTime.toLocalDate();
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

        logger.debug("Getting working hours for date: {}, dayOfWeek: {}, groupId: {}",
                currentDate, dayOfWeek, assigneeGroupId);

        Holiday holiday = holidayRepository
                .findByAssigneeGroupIdAndHolidayDateAndActive(
                        assigneeGroupId, currentDate, "Y"
                )
                .orElse(null);

        if (holiday != null) {
            logger.debug("Found holiday: {} (type: {})",
                    holiday.getHolidayName(), holiday.getWorkHourType());

            if (holiday.getWorkHourType() == WokingHourTypeEnum.DAY_OFF) {
                logger.debug("Holiday is DAY_OFF");
                return null;
            } else {
                WorkingHours wh = new WorkingHours();
                wh.setStartTime(holiday.getStartTime());
                wh.setEndTime(holiday.getEndTime());
                wh.setWorkHourType(holiday.getWorkHourType());
                logger.debug("Holiday working hours: {} - {}", wh.getStartTime(), wh.getEndTime());
                return wh;
            }
        }

        WorkingHours regularHours = workingHoursRepository
                .findByAssigneeGroupIdAndDayOfWeekAndActive(
                        assigneeGroupId, dayOfWeek, "Y"
                )
                .orElse(null);

        if (regularHours != null) {
            if (regularHours.getWorkHourType() == WokingHourTypeEnum.DAY_OFF) {
                logger.debug("Regular day is DAY_OFF");
                return null;
            }
            logger.debug("Regular working hours: {} - {} (type: {})",
                    regularHours.getStartTime(),
                    regularHours.getEndTime(),
                    regularHours.getWorkHourType());
            return regularHours;
        }

        logger.debug("No working hours found");
        return null;
    }


    private LocalDateTime getNextWorkingDay(LocalDateTime currentDateTime,
                                            Integer assigneeGroupId) {
        LocalDate nextDate = currentDateTime.toLocalDate().plusDays(1);
        int maxDays = 365;
        int daysChecked = 0;

        while (daysChecked < maxDays) {
            daysChecked++;

            logger.debug("Checking next working day: {}", nextDate);

            LocalDateTime nextDateTime = LocalDateTime.of(nextDate, LocalTime.of(0, 0));
            WorkingHours nextWorkingHours = getCurrentDayWorkingHours(nextDateTime, assigneeGroupId);

            if (nextWorkingHours != null) {
                LocalDateTime result = LocalDateTime.of(nextDate, nextWorkingHours.getStartTime());
                logger.debug("Found next working day: {} at {}", nextDate, nextWorkingHours.getStartTime());
                return result;
            }

            logger.debug("{} is not a working day, checking next day", nextDate);
            nextDate = nextDate.plusDays(1);
        }

        logger.error("Could not find working day within {} days", maxDays);
        throw new RuntimeException("Could not find next working day within " + maxDays + " days");
    }
}
package org.example.incidentmanagement.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RequestTimeUnitEnums {
    WORKING_MINUTES("Working Minutes"),
    WORKING_HOURS("Working Hours"),
    WORKING_DAYS("Working Days"),
    CALENDAR_MINUTES("Calendar Minutes"),
    CALENDAR_HOURS("Calendar Hours"),
    CALENDAR_DAYS("Calendar Days");

    private final String displayValue;

    RequestTimeUnitEnums(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonValue
    public String getDisplayValue() {
        return displayValue;
    }

    @JsonCreator
    public static RequestTimeUnitEnums fromString(String text) {
        for (RequestTimeUnitEnums unit : values()) {
            if (unit.displayValue.equals(text)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Unknown time unit: " + text);
    }
}
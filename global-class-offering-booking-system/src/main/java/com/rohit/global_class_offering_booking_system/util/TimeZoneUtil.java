package com.rohit.global_class_offering_booking_system.util;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeZoneUtil {

    private TimeZoneUtil() {}

    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a z");

    // UTC Instant -> parent's local wall-clock time, formatted for display
    public static String convertToZone(Instant utcTime, String zoneId) {
        return utcTime.atZone(toZone(zoneId)).format(DISPLAY_FORMAT);
    }

    // teacher's local wall-clock STRING + their zone -> UTC Instant for storage
    public static Instant toUtcInstant(String localDateTime, String zoneId) {
        LocalDateTime parsed;
        try {
            parsed = LocalDateTime.parse(localDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid datetime format: " + localDateTime +
                            " (expected ISO local, e.g. 2026-06-06T18:00:00)");
        }
        return parsed.atZone(toZone(zoneId)).toInstant();
    }

    // validates the zone string; throws a clean error for bad input
    public static ZoneId toZone(String zoneId) {
        if (zoneId == null || zoneId.isBlank()) {
            throw new IllegalArgumentException("Timezone must not be empty");
        }
        try {
            return ZoneId.of(zoneId);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid or unknown timezone: " + zoneId);
        }
    }
}
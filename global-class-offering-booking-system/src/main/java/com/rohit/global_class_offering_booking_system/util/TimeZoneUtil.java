package com.rohit.global_class_offering_booking_system.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneUtil {
    private TimeZoneUtil() {}

    public static String convertToZone(
            Instant utcTime,
            String zoneId) {

        ZonedDateTime zonedDateTime = utcTime.atZone(ZoneId.of("UTC"))
                           .withZoneSameInstant(ZoneId.of(zoneId));

        return zonedDateTime.toString();
    }
}

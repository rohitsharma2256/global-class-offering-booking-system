package com.rohit.global_class_offering_booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddSessionRequest {

    @NotBlank(message = "startTime is required (ISO local format, e.g. 2026-06-06T18:00:00)")
    private String startTime;

    @NotBlank(message = "endTime is required (ISO local format, e.g. 2026-06-06T19:00:00)")
    private String endTime;

    @NotBlank(message = "teacherTimezone is required (e.g. Asia/Kolkata)")
    private String teacherTimezone;
}
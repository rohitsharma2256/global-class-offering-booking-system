package com.rohit.global_class_offering_booking_system.dto.request;

import lombok.Data;

@Data
public class AddSessionRequest {

    private String startTime;

    private String endTime;

    private String teacherTimezone;
}
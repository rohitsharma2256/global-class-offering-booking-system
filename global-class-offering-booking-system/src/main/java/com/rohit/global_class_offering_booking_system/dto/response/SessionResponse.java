package com.rohit.global_class_offering_booking_system.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponse {
    private  Long sessionId;
    private String startTime;

    private String endTime;
}

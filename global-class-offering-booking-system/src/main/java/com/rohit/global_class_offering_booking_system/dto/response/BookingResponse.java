package com.rohit.global_class_offering_booking_system.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private Long bookingId;
    private Long offeringId;
    private String message;
}

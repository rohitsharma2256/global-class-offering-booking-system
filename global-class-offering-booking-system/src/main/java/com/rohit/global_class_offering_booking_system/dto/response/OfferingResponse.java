package com.rohit.global_class_offering_booking_system.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OfferingResponse {
    private Long offeringId;

    private String courseName;

    private String batchName;

    private String teacherName;
    private List<SessionResponse> sessions;

}

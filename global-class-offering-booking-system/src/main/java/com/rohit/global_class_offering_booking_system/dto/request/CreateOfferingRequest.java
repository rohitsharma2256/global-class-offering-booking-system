package com.rohit.global_class_offering_booking_system.dto.request;


import lombok.Data;

@Data
public class CreateOfferingRequest {
    private Long courseId;
    private Long teacherId;
    private String batchName;
}

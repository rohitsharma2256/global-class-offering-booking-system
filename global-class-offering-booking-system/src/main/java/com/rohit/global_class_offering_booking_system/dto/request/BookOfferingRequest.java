package com.rohit.global_class_offering_booking_system.dto.request;

import lombok.Data;

@Data
public class BookOfferingRequest {

    private Long parentId;

    private Long offeringId;
}
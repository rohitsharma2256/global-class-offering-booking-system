package com.rohit.global_class_offering_booking_system.service;

import com.rohit.global_class_offering_booking_system.dto.request.BookOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.BookingResponse;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;

import java.util.List;

public interface ParentService {
    List<OfferingResponse> getAvailableOfferings(String parentTimeZone);

    BookingResponse bookOffering(
            BookOfferingRequest request);

    List<BookingResponse> getBookings(
            Long parentId);
}

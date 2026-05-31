package com.rohit.global_class_offering_booking_system.service;


import com.rohit.global_class_offering_booking_system.dto.request.AddSessionRequest;
import com.rohit.global_class_offering_booking_system.dto.request.CreateOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;

import  java.util.List;

public interface TeacherService {

    OfferingResponse createOffering(
            CreateOfferingRequest request);

    void addSession(
            Long offeringId,
            AddSessionRequest request);

    List<OfferingResponse> getTeacherOfferings(
            Long teacherId);
}
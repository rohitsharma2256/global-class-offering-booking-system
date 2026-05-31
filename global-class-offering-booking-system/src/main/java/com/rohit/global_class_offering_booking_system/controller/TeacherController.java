package com.rohit.global_class_offering_booking_system.controller;


import com.rohit.global_class_offering_booking_system.dto.request.AddSessionRequest;
import com.rohit.global_class_offering_booking_system.dto.request.CreateOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;
import com.rohit.global_class_offering_booking_system.service.TeacherService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
     //create offering
    @PostMapping("/offerings")
    public OfferingResponse createOffering(
            @RequestBody CreateOfferingRequest request) {

        return teacherService.createOffering(request);
    }

    //add sessions to offering
    @PostMapping("/offerings/{offeringId}/sessions")
    public String addSession(
            @PathVariable Long offeringId,
            @RequestBody AddSessionRequest request) {

        teacherService.addSession(
                offeringId,
                request);

        return "Session added successfully";
    }

    //get teacher offering
    @GetMapping("/{teacherId}/offerings")
    public List<OfferingResponse> getTeacherOfferings(
            @PathVariable Long teacherId) {

        return teacherService.getTeacherOfferings(
                teacherId);
    }
}

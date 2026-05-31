package com.rohit.global_class_offering_booking_system.controller;


import com.rohit.global_class_offering_booking_system.dto.request.BookOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.BookingResponse;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;
import com.rohit.global_class_offering_booking_system.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    //get available offering
    @GetMapping("/offerings")
    public ResponseEntity<List<OfferingResponse>> getAvailableOfferings(@RequestParam String timezone) {

        return ResponseEntity.ok(parentService.getAvailableOfferings(timezone));
    }

    //for booking offering
    @PostMapping("/book")
    public BookingResponse bookOffering(@RequestBody BookOfferingRequest request){

        return parentService.bookOffering(request);
    }
   //get bookings.

    @GetMapping("/{parentId}/booking")
    public List<BookingResponse> getBookings(@PathVariable Long parentId){
        return parentService.getBookings(parentId);
    }
}

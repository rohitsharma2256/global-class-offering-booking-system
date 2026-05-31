package com.rohit.global_class_offering_booking_system.service.impl;

import com.rohit.global_class_offering_booking_system.dto.request.BookOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.BookingResponse;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;
import com.rohit.global_class_offering_booking_system.dto.response.SessionResponse;
import com.rohit.global_class_offering_booking_system.entity.Booking;
import com.rohit.global_class_offering_booking_system.entity.Offering;
import com.rohit.global_class_offering_booking_system.entity.Parent;
import com.rohit.global_class_offering_booking_system.entity.Session;
import com.rohit.global_class_offering_booking_system.exception.ConflictException;
import com.rohit.global_class_offering_booking_system.exception.ResourceNotFoundException;
import com.rohit.global_class_offering_booking_system.repository.BookingRepo;
import com.rohit.global_class_offering_booking_system.repository.OfferingRepo;
import com.rohit.global_class_offering_booking_system.repository.ParentRepo;
import com.rohit.global_class_offering_booking_system.repository.SessionRepo;
import com.rohit.global_class_offering_booking_system.service.ParentService;
import com.rohit.global_class_offering_booking_system.util.TimeZoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepo parentRepo;
    private final OfferingRepo offeringRepo;
    private final BookingRepo bookingRepo;
    private final SessionRepo sessionRepo;
    @Override
    public List<OfferingResponse> getAvailableOfferings(
            String parentTimeZone) {

        return offeringRepo.findAll()
                .stream()
                .map(offering -> {

                    List<SessionResponse> sessions = offering.getSessions()
                                    .stream()
                                    .map(session -> SessionResponse.builder()
                                                    .sessionId(session.getId())
                                                    .startTime(TimeZoneUtil.convertToZone(session.getStartTime(), parentTimeZone))
                                                    .endTime(TimeZoneUtil.convertToZone(session.getEndTime(), parentTimeZone))
                                                    .build())
                            .collect(Collectors.toList());


                    return OfferingResponse.builder()
                            .offeringId(offering.getId())
                            .courseName(offering.getCourse().getCourseName())
                            .batchName(offering.getBatchName())
                            .teacherName(offering.getTeacher().getName())
                            .sessions(sessions)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookingResponse bookOffering(
            BookOfferingRequest request) {

        Parent parent =
                parentRepo.findParentForUpdate(request.getParentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Parent not found"));

        Offering offering =
                offeringRepo.findById(request.getOfferingId())
                        .orElseThrow(() -> new ResourceNotFoundException("Offering not found"));

        if (bookingRepo.existsByParentIdAndOfferingId(parent.getId(), offering.getId())) {
            throw new ConflictException("Offering already booked");
        }

        validateNoSessionConflict(parent.getId(), offering.getId());

        Booking booking = Booking.builder()
                        .parent(parent)
                        .offering(offering)
                        .bookedAt(Instant.now())
                        .build();

        bookingRepo.save(booking);

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .offeringId(offering.getId())
                .message("Booking successful")
                .build();
    }

    private void validateNoSessionConflict(Long parentId, Long newOfferingId) {
        List<Booking> existingBookings = bookingRepo.findByParentId(parentId);

        List<Session> newSessions = sessionRepo.findByOfferingId(newOfferingId);

        //foreach loop
        for (Booking booking : existingBookings) {
            List<Session> bookedSessions = sessionRepo.findByOfferingId(booking.getOffering().getId());

            for (Session booked : bookedSessions) {

                for (Session incoming : newSessions) {

                    boolean overlap = booked.getStartTime()
                            .isBefore(incoming.getEndTime()) && incoming.getStartTime()
                            .isBefore(booked.getEndTime());

                    if (overlap) {
                        throw new ConflictException("Session conflict detected");
                    }
                }
            }
        }
    }

    @Override
    public List<BookingResponse> getBookings(
            Long parentId) {

        return bookingRepo.findByParentId(parentId)
                .stream()
                .map(booking -> BookingResponse.builder()
                                .bookingId(booking.getId())
                                .offeringId(booking.getOffering().getId())
                                .message(booking.getOffering().getBatchName())
                                .build())
                .collect(Collectors.toList());
    }
}


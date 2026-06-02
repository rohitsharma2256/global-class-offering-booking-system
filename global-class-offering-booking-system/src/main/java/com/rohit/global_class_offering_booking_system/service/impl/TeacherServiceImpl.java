package com.rohit.global_class_offering_booking_system.service.impl;

import com.rohit.global_class_offering_booking_system.dto.request.AddSessionRequest;
import com.rohit.global_class_offering_booking_system.dto.request.CreateOfferingRequest;
import com.rohit.global_class_offering_booking_system.dto.response.OfferingResponse;
import com.rohit.global_class_offering_booking_system.entity.Course;
import com.rohit.global_class_offering_booking_system.entity.Offering;
import com.rohit.global_class_offering_booking_system.entity.Session;
import com.rohit.global_class_offering_booking_system.entity.Teacher;
import com.rohit.global_class_offering_booking_system.exception.ResourceNotFoundException;
import com.rohit.global_class_offering_booking_system.repository.CourseRepo;
import com.rohit.global_class_offering_booking_system.repository.OfferingRepo;
import com.rohit.global_class_offering_booking_system.repository.SessionRepo;
import com.rohit.global_class_offering_booking_system.repository.TeacherRepo;
import com.rohit.global_class_offering_booking_system.service.TeacherService;
import com.rohit.global_class_offering_booking_system.util.TimeZoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;
    private final CourseRepo courseRepo;
    private final OfferingRepo offeringRepo;
    private final SessionRepo sessionRepo;

    @Override
    public OfferingResponse createOffering(
            CreateOfferingRequest request) {

        Teacher teacher = teacherRepo.findById(
                        request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Course course = courseRepo.findById(
                        request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));


        Offering offering = Offering.builder()
                .batchName(request.getBatchName())
                .teacher(teacher)
                .course(course)
                .build();

        offering = offeringRepo.save(offering);

        return OfferingResponse.builder()
                .offeringId(offering.getId())
                .courseName(course.getCourseName())
                .batchName(offering.getBatchName())
                .teacherName(teacher.getName())
                .build();
    }

    @Override
    public void addSession(Long offeringId, AddSessionRequest request) {

        Offering offering = offeringRepo.findById(offeringId)
                .orElseThrow(() -> new ResourceNotFoundException("Offering not found"));

        Instant startUtc = TimeZoneUtil.toUtcInstant(
                request.getStartTime(), request.getTeacherTimezone());
        Instant endUtc = TimeZoneUtil.toUtcInstant(
                request.getEndTime(), request.getTeacherTimezone());

        if (!endUtc.isAfter(startUtc)) {
            throw new IllegalArgumentException("Session endTime must be after startTime");
        }

        Session session = Session.builder()
                .offering(offering)
                .startTime(startUtc)
                .endTime(endUtc)
                .build();

        sessionRepo.save(session);
    }

    @Override
    public List<OfferingResponse> getTeacherOfferings(
            Long teacherId) {

        return offeringRepo.findByTeacherId(teacherId)
                .stream()
                .map(offering -> OfferingResponse.builder()
                                .offeringId(offering.getId())
                                .courseName(offering.getCourse().getCourseName())
                                .batchName(offering.getBatchName())
                                .teacherName(offering.getTeacher().getName())
                                .build())
                .collect(Collectors.toList());
    }
}





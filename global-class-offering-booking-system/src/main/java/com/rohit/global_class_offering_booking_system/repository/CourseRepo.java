package com.rohit.global_class_offering_booking_system.repository;


import com.rohit.global_class_offering_booking_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}

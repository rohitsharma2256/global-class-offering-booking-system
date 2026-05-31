package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}

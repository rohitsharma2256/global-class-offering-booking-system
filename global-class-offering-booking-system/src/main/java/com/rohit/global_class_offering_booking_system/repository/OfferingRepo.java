package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Offering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferingRepo  extends JpaRepository<Offering, Long> {
    //@Override
    List<Offering> findByTeacherId(Long teacherId);
}

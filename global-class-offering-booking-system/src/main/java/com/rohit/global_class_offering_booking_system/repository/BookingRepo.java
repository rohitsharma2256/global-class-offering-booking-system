package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findByParentId(Long parentId);

    boolean existsByParentIdAndOfferingId(Long parentId, Long offeringId);
}

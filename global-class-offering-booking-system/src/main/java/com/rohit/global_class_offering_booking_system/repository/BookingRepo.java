package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    @Query("""
    SELECT COUNT(s) > 0
    FROM Booking b
    JOIN Session s ON s.offering = b.offering
    JOIN Session newS ON newS.offering.id = :newOfferingId
    WHERE b.parent.id = :parentId
      AND s.startTime < newS.endTime
      AND newS.startTime < s.endTime
    """)
    boolean hasSessionConflict(@Param("parentId") Long parentId, @Param("newOfferingId") Long newOfferingId);
    List<Booking> findByParentId(Long parentId);

    boolean existsByParentIdAndOfferingId(Long parentId, Long offeringId);
}

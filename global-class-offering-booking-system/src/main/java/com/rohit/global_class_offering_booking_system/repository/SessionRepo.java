package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepo  extends JpaRepository<Session, Long> {

    List<Session> findByOfferingId(Long offeringId);
}

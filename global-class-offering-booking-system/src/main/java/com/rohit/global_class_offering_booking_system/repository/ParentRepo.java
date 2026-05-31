package com.rohit.global_class_offering_booking_system.repository;

import com.rohit.global_class_offering_booking_system.entity.Parent;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParentRepo  extends JpaRepository<Parent, Long> {

    //Handling the concurrent locking parent
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT p
           FROM Parent p
           WHERE p.id = :parentId
           """)
    Optional<Parent> findParentForUpdate(
            Long parentId);

}

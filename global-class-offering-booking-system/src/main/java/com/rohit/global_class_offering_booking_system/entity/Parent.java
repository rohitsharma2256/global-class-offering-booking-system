package com.rohit.global_class_offering_booking_system.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String timeZone;
}

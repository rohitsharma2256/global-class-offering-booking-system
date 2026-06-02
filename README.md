# Global Class Offering Booking System

## Project Overview

A backend service for a global live-learning platform where teachers conduct online classes and parents/students book course offerings.

The system supports:

- Courses
- Offerings (Batches)
- Sessions
- Parent Bookings
- Timezone Conversion
- Conflict Detection
- Concurrent Booking Handling

---

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## Features

### Teacher APIs

- Create Offering
- Add Sessions to Offering
- View Teacher Offerings

### Parent APIs

- View Available Offerings
- Book Offering
- View Bookings

---

## Booking Rules

### Rule 1

Booking happens at Offering level.

Parents book the complete offering and all sessions inside it.

### Rule 2

Session Conflict Detection

If any session overlaps with an already booked offering, booking is rejected.

### Rule 3

Concurrent Booking Handling

Pessimistic Locking is used on Parent records to prevent simultaneous conflicting bookings.

---

## Timezone Handling

Teachers create sessions in their own timezone.

Sessions are stored in UTC in database.

Parents view sessions in their local timezone.

Example:

Teacher Timezone:
Asia/Kolkata

Parent Timezone:
America/New_York

System automatically converts session timings.

---

## Database Design

Entities:

- Teacher
- Parent
- Course
- Offering
- Session
- Booking

Relationships:

Course -> Offering (One To Many)

Teacher -> Offering (One To Many)

Offering -> Session (One To Many)

Parent -> Booking (One To Many)

Offering -> Booking (One To Many)

---

## Environment Variables

Update application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/class_booking_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

## Run Application

Clone Repository

```bash
git clone https://github.com/rohitsharma2256/global-class-offering-booking-system.git
```

Open project in IntelliJ IDEA

Run:

```bash
mvn clean install
```

Start:

```bash
GlobalClassOfferingBookingSystemApplication
```

Server:

```text
http://localhost:8080
```

---

## Assumptions

- Parent books full offering
- Sessions cannot overlap with existing booked sessions
- Teacher and Parent already exist in database
- Course already exists in database
- Session times are stored in UTC

---

## Concurrency Handling

Implemented using:

- @Transactional
- Pessimistic Locking
- Unique Constraint on Booking table

This prevents:

- Duplicate booking
- Simultaneous conflicting booking requests

---

## Timezone Conversion Strategy

Teacher Input

Local Time
+
Teacher Timezone

↓

Converted to UTC

↓

Stored in Database

↓

Converted to Parent Timezone when displayed

---

## Future Improvements

- JWT Authentication
- Role Based Access
- Flyway Migration
- Swagger Documentation
- Docker Deployment
- Kubernetes Deployment

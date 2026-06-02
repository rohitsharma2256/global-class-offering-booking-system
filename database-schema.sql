CREATE TABLE teachers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    timezone VARCHAR(100)
);

CREATE TABLE parents (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    time_zone VARCHAR(100)
);

CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(255),
    description VARCHAR(500)
);

CREATE TABLE offerings (
    id BIGSERIAL PRIMARY KEY,
    batch_name VARCHAR(255),
    teacher_id BIGINT,
    course_id BIGINT,

    CONSTRAINT fk_teacher
        FOREIGN KEY (teacher_id)
        REFERENCES teachers(id),

    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
        REFERENCES courses(id)
);

CREATE TABLE sessions (
    id BIGSERIAL PRIMARY KEY,
    offering_id BIGINT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,

    CONSTRAINT fk_offering
        FOREIGN KEY (offering_id)
        REFERENCES offerings(id)
);

CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    parent_id BIGINT,
    offering_id BIGINT,
    booked_at TIMESTAMP,

    CONSTRAINT fk_parent
        FOREIGN KEY (parent_id)
        REFERENCES parents(id),

    CONSTRAINT fk_booking_offering
        FOREIGN KEY (offering_id)
        REFERENCES offerings(id),

    CONSTRAINT unique_parent_offering
        UNIQUE(parent_id, offering_id)
);

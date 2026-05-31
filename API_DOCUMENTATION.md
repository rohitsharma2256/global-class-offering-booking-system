# API Documentation

Base URL

```text
http://localhost:8080
```

---

# Teacher APIs

## Create Offering

POST

```text
/api/teachers/offerings
```

Request

```json
{
  "courseId": 1,
  "teacherId": 1,
  "batchName": "Saturday Batch"
}
```

---

## Add Session

POST

```text
/api/teachers/offerings/{offeringId}/sessions
```

Request

```json
{
  "startTime": "2026-06-06T18:00:00",
  "endTime": "2026-06-06T19:00:00",
  "teacherTimezone": "Asia/Kolkata"
}
```

---

## Get Teacher Offerings

GET

```text
/api/teachers/{teacherId}/offerings
```

---

# Parent APIs

## Get Available Offerings

GET

```text
/api/parents/offerings?timezone=Asia/Kolkata
```

---

## Book Offering

POST

```text
/api/parents/book
```

Request

```json
{
  "parentId": 1,
  "offeringId": 1
}
```

---

## Get Bookings

GET

```text
/api/parents/{parentId}/booking
```

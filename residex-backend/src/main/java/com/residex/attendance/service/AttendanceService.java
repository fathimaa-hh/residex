package com.residex.attendance.service;
import com.residex.common.enums.AttendanceStatus;

public interface AttendanceService {

    

    void markAttendance(
        Long studentId,
        AttendanceStatus status
);
}
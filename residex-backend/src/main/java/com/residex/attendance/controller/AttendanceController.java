package com.residex.attendance.controller;

import com.residex.attendance.dto.MarkAttendanceRequest;
import com.residex.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public String markAttendance(
            @RequestBody MarkAttendanceRequest request
    ) {

        attendanceService.markAttendance(
            request.getStudentId(),
            request.getStatus()
    );

        return "Attendance Marked";
    }
}
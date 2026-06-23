package com.residex.attendance.controller;

import com.residex.attendance.dto.MarkAttendanceRequest;
import com.residex.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Attendance",
        description = "Hostel attendance tracking"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    @Operation(
            summary = "Mark attendance"
    )
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
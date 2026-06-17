package com.residex.attendance.dto;

import com.residex.common.enums.AttendanceStatus;
import lombok.Data;

@Data
public class MarkAttendanceRequest {

    private Long studentId;

    private AttendanceStatus status;
}
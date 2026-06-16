package com.residex.leave.dto;

import com.residex.common.enums.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateLeaveRequest {

    private Long studentId;

    private LeaveType leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String reason;

    private String destination;

    private Boolean parentConsent;
}
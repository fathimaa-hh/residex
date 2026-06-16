package com.residex.leave.dto;

import com.residex.common.enums.LeaveStatus;
import com.residex.common.enums.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LeaveResponse {

    private Long id;

    private String studentName;

    private LeaveType leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer totalDays;

    private String reason;

    private String destination;

    private LeaveStatus status;
}
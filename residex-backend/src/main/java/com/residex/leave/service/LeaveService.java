package com.residex.leave.service;

import com.residex.leave.dto.CreateLeaveRequest;
import com.residex.leave.dto.LeaveResponse;

import java.util.List;

public interface LeaveService {

    LeaveResponse applyLeave(
            CreateLeaveRequest request
    );

    

    List<LeaveResponse> getAllLeaves();
    LeaveResponse approveLeave(
            Long leaveId
    );

    LeaveResponse rejectLeave(
            Long leaveId
    );

    List<LeaveResponse> getPendingLeaves();
}
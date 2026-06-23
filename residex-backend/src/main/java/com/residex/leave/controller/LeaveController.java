package com.residex.leave.controller;

import com.residex.leave.dto.CreateLeaveRequest;
import com.residex.leave.dto.LeaveResponse;
import com.residex.leave.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Leave Management",
        description = "Leave request and approval workflow"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @Operation(
            summary = "Create leave request"
    )
    @PostMapping
    public LeaveResponse applyLeave(
            @RequestBody CreateLeaveRequest request
    ) {
        return leaveService.applyLeave(request);
    }
    @Operation(
            summary = "Get all leave requests"
    )
    @GetMapping
    public List<LeaveResponse> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
    @Operation(
            summary = "Get all pending leave requests"
    )
    @GetMapping("/pending")
    public List<LeaveResponse> pendingLeaves() {

        return leaveService.getPendingLeaves();
    }
    @Operation(
            summary = "Approve leave request"
    )
    @PutMapping("/{id}/approve")
    public LeaveResponse approveLeave(
            @PathVariable Long id
    ) {

        return leaveService.approveLeave(id);
    }
    @Operation(
            summary = "Reject leave request"
    )
    @PutMapping("/{id}/reject")
    public LeaveResponse rejectLeave(
            @PathVariable Long id
    ) {

        return leaveService.rejectLeave(id);
    }
}
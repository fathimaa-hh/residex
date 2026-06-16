package com.residex.leave.controller;

import com.residex.leave.dto.CreateLeaveRequest;
import com.residex.leave.dto.LeaveResponse;
import com.residex.leave.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping
    public LeaveResponse applyLeave(
            @RequestBody CreateLeaveRequest request
    ) {
        return leaveService.applyLeave(request);
    }

    @GetMapping
    public List<LeaveResponse> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
}
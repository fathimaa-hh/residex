package com.residex.leave.repository;

import com.residex.leave.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {
}
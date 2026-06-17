package com.residex.leave.repository;

import com.residex.leave.entity.LeaveRequest;
import com.residex.common.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByStatus(
            LeaveStatus status
    );
}
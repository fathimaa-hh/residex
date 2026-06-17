package com.residex.leave.service.impl;

import com.residex.common.enums.LeaveStatus;
import com.residex.leave.dto.CreateLeaveRequest;
import com.residex.leave.dto.LeaveResponse;
import com.residex.leave.entity.LeaveRequest;
import com.residex.leave.repository.LeaveRequestRepository;
import com.residex.leave.service.LeaveService;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRepository;
    private final StudentRepository studentRepository;

    @Override
    public LeaveResponse applyLeave(
            CreateLeaveRequest request
    ) {

        Student student =
                studentRepository.findById(
                        request.getStudentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        ));

        LeaveRequest leave = new LeaveRequest();

        leave.setStudent(student);

        leave.setLeaveType(
                request.getLeaveType()
        );

        leave.setFromDate(
                request.getFromDate()
        );

        leave.setToDate(
                request.getToDate()
        );

        long days =
                ChronoUnit.DAYS.between(
                        request.getFromDate(),
                        request.getToDate()
                ) + 1;

        leave.setTotalDays((int) days);

        leave.setReason(
                request.getReason()
        );

        leave.setDestination(
                request.getDestination()
        );

        leave.setParentConsent(
                request.getParentConsent()
        );

        leave.setStatus(
                LeaveStatus.PENDING
        );

        leaveRepository.save(leave);

        return mapToResponse(leave);
    }

    @Override
    public List<LeaveResponse> getAllLeaves() {

        return leaveRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private LeaveResponse mapToResponse(
            LeaveRequest leave
    ) {

        return LeaveResponse.builder()
                .id(leave.getId())
                .studentName(
                        leave.getStudent()
                                .getUser()
                                .getName()
                )
                .leaveType(
                        leave.getLeaveType()
                )
                .fromDate(
                        leave.getFromDate()
                )
                .toDate(
                        leave.getToDate()
                )
                .totalDays(
                        leave.getTotalDays()
                )
                .reason(
                        leave.getReason()
                )
                .destination(
                        leave.getDestination()
                )
                .status(
                        leave.getStatus()
                )
                .build();
    }
    @Override
        public List<LeaveResponse> getPendingLeaves() {

        return leaveRepository
                .findByStatus(
                        LeaveStatus.PENDING
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
        }

        @Override
        public LeaveResponse approveLeave(
                Long leaveId
        ) {

        LeaveRequest leave =
                leaveRepository.findById(leaveId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave not found"
                                ));

        leave.setStatus(
                LeaveStatus.APPROVED
        );

        leaveRepository.save(leave);

        return mapToResponse(leave);
        }
        @Override
        public LeaveResponse rejectLeave(
                Long leaveId
        ) {

        LeaveRequest leave =
                leaveRepository.findById(leaveId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave not found"
                                ));

        leave.setStatus(
                LeaveStatus.REJECTED
        );

        leaveRepository.save(leave);

        return mapToResponse(leave);
        }
}
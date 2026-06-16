package com.residex.leave.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.common.enums.LeaveStatus;
import com.residex.common.enums.LeaveType;
import com.residex.student.entity.Student;
import com.residex.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "leave_requests")
public class LeaveRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer totalDays;

    @Column(columnDefinition = "TEXT")
    private String reason;

    private String destination;

    private Boolean parentConsent;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private LocalDateTime approvedAt;
}
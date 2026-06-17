package com.residex.attendance.entity;

import com.residex.common.entity.BaseEntity;
import com.residex.common.enums.AttendanceStatus;
import com.residex.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "attendance_records")
public class AttendanceRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
}
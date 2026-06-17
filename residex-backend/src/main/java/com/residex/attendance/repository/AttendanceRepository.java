package com.residex.attendance.repository;

import com.residex.attendance.entity.AttendanceRecord;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository
        extends JpaRepository<AttendanceRecord, Long> {
            List<AttendanceRecord> findByStudentIdAndAttendanceDateBetween(
                Long studentId,
                LocalDate startDate,
                LocalDate endDate
        );
}
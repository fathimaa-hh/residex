package com.residex.stayrecord.service.impl;

import com.residex.attendance.entity.AttendanceRecord;
import com.residex.attendance.repository.AttendanceRepository;
import com.residex.common.enums.AttendanceStatus;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import com.residex.stayrecord.entity.StayRecord;
import com.residex.stayrecord.repository.StayRecordRepository;
import com.residex.stayrecord.service.StayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayServiceImpl implements StayService {

    private final StudentRepository studentRepository;

    private final AttendanceRepository attendanceRepository;

    private final StayRecordRepository stayRecordRepository;

    @Override
    public void generateMonthlyStayRecord(
            Long studentId,
            int month,
            int year
    ) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"
                                ));

        LocalDate startDate =
                LocalDate.of(year, month, 1);

        LocalDate endDate =
                startDate.withDayOfMonth(
                        startDate.lengthOfMonth()
                );

        List<AttendanceRecord> records =
                attendanceRepository
                        .findByStudentIdAndAttendanceDateBetween(
                                studentId,
                                startDate,
                                endDate
                        );

        long stayedDays =
                records.stream()
                        .filter(r ->
                                r.getStatus()
                                        == AttendanceStatus.IN_HOSTEL)
                        .count();

        long approvedLeave =
                records.stream()
                        .filter(r ->
                                r.getStatus()
                                        == AttendanceStatus.ON_APPROVED_LEAVE)
                        .count();

        long unauthorized =
                records.stream()
                        .filter(r ->
                                r.getStatus()
                                        == AttendanceStatus.UNAUTHORIZED_ABSENCE)
                        .count();

        StayRecord stayRecord =
                new StayRecord();

        stayRecord.setStudent(student);

        stayRecord.setMonth(month);

        stayRecord.setYear(year);

        stayRecord.setStayedDays(
                (int) stayedDays
        );

        stayRecord.setApprovedLeaveDays(
                (int) approvedLeave
        );

        stayRecord.setUnauthorizedAbsenceDays(
                (int) unauthorized
        );

        stayRecord.setTotalDays(
                records.size()
        );

        stayRecordRepository.save(
                stayRecord
        );
    }
}
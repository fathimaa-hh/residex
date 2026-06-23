package com.residex.attendance.service.impl;

import com.residex.attendance.entity.AttendanceRecord;
import com.residex.attendance.repository.AttendanceRepository;
import com.residex.attendance.service.AttendanceService;
import com.residex.common.enums.AttendanceStatus;
import com.residex.exception.ResourceNotFoundException;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl
        implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentRepository studentRepository;

    @Override
    public void markAttendance(
            Long studentId,
            AttendanceStatus status
    ) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"
                                )
                                );

        AttendanceRecord record =
                new AttendanceRecord();

        record.setStudent(student);

        record.setAttendanceDate(
                LocalDate.now()
        );

        record.setStatus(status);

        attendanceRepository.save(record);
    }
}
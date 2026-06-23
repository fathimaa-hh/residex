package com.residex.feerecord.service.impl;


import com.residex.exception.ResourceNotFoundException;
import com.residex.feerecord.entity.FeeRecord;
import com.residex.feerecord.repository.FeeRecordRepository;
import com.residex.feerecord.service.FeeService;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import com.residex.stayrecord.entity.StayRecord;
import com.residex.stayrecord.repository.StayRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl
        implements FeeService {

    private final StudentRepository studentRepository;

    private final StayRecordRepository stayRecordRepository;

    private final FeeRecordRepository feeRecordRepository;

    @Override
    public void generateMonthlyFee(
            Long studentId,
            int month,
            int year
    ) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"
                                )
                                );

        StayRecord stayRecord =
                stayRecordRepository
                        .findByStudentIdAndMonthAndYear(
                                studentId,
                                month,
                                year
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Stay record not found"
                                ));

        double feePerDay =
                student.getRoom()
                        .getResidence()
                        .getFeePerDay();

        double baseFee =
                feePerDay
                        * stayRecord.getStayedDays();

        double penalty =
                stayRecord
                        .getUnauthorizedAbsenceDays()
                        * 100.0;

        double deductions = 0.0;

        double finalAmount =
                baseFee
                        + penalty
                        - deductions;

        FeeRecord feeRecord =
                new FeeRecord();

        feeRecord.setStudent(student);

        feeRecord.setMonth(month);

        feeRecord.setYear(year);

        feeRecord.setBaseFee(baseFee);

        feeRecord.setPenalties(penalty);

        feeRecord.setDeductions(deductions);

        feeRecord.setFinalAmount(finalAmount);

        feeRecord.setPaid(false);

        feeRecordRepository.save(feeRecord);
    }
}

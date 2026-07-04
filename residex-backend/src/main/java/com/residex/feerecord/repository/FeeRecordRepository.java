package com.residex.feerecord.repository;

import com.residex.feerecord.entity.FeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeRecordRepository
        extends JpaRepository<FeeRecord, Long> {

    Optional<FeeRecord>
    findByStudentIdAndMonthAndYear(

            Long studentId,

            Integer month,

            Integer year

    );

}
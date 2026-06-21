package com.residex.stayrecord.repository;

import com.residex.stayrecord.entity.StayRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StayRecordRepository
        extends JpaRepository<StayRecord, Long> {
                Optional<StayRecord>
    findByStudentIdAndMonthAndYear(
            Long studentId,
            Integer month,
            Integer year
    );
}







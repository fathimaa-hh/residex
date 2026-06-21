package com.residex.feerecord.repository;



import com.residex.feerecord.entity.FeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRecordRepository
        extends JpaRepository<FeeRecord, Long> {
}


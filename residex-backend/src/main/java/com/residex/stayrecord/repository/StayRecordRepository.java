package com.residex.stayrecord.repository;

import com.residex.stayrecord.entity.StayRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayRecordRepository
        extends JpaRepository<StayRecord, Long> {
}

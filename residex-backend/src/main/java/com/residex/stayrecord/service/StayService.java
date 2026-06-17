package com.residex.stayrecord.service;

public interface StayService {

    void generateMonthlyStayRecord(
            Long studentId,
            int month,
            int year
    );
}
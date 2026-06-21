package com.residex.feerecord.service;


public interface FeeService {

    void generateMonthlyFee(
            Long studentId,
            int month,
            int year
    );
}

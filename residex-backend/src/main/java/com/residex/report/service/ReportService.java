package com.residex.report.service;

public interface ReportService {

    byte[] generateMonthlyReport(

            Long studentId,

            Integer month,

            Integer year

    );

}
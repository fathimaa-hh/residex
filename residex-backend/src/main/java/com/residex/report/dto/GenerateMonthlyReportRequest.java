package com.residex.report.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateMonthlyReportRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

}
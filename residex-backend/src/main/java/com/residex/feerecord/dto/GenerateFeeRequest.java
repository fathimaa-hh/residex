package com.residex.feerecord.dto;

import lombok.Data;

@Data
public class GenerateFeeRequest {

    private Long studentId;

    private Integer month;

    private Integer year;
}

package com.residex.stayrecord.dto;

import lombok.Data;

@Data
public class GenerateStayRequest {

    private Long studentId;

    private Integer month;

    private Integer year;
}
package com.residex.stayrecord.controller;

import com.residex.stayrecord.dto.GenerateStayRequest;
import com.residex.stayrecord.service.StayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stay")
@RequiredArgsConstructor
public class StayController {

    private final StayService stayService;

    @PostMapping("/generate")
    public String generateStayRecord(
            @RequestBody GenerateStayRequest request
    ) {

        stayService.generateMonthlyStayRecord(
                request.getStudentId(),
                request.getMonth(),
                request.getYear()
        );

        return "Stay record generated successfully";
    }
}
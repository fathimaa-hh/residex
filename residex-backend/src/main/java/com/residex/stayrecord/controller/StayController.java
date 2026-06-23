package com.residex.stayrecord.controller;

import com.residex.stayrecord.dto.GenerateStayRequest;
import com.residex.stayrecord.service.StayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Stay Records",
        description = "Monthly stay calculations"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/stay")
@RequiredArgsConstructor
public class StayController {

    private final StayService stayService;
    @Operation(
            summary = "Generate monthly stay record"
    )
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
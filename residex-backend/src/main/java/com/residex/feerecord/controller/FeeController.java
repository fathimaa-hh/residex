package com.residex.feerecord.controller;



import com.residex.feerecord.dto.GenerateFeeRequest;
import com.residex.feerecord.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Fee Management",
        description = "Monthly fee generation"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;
    @Operation(
            summary = "Generate monthly fee"
    )
    @PostMapping("/generate")
    public String generateFee(
            @RequestBody GenerateFeeRequest request
    ) {

        feeService.generateMonthlyFee(
                request.getStudentId(),
                request.getMonth(),
                request.getYear()
        );

        return "Fee generated successfully";
    }
}

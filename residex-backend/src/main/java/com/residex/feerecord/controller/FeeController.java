package com.residex.feerecord.controller;



import com.residex.feerecord.dto.GenerateFeeRequest;
import com.residex.feerecord.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;

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

package com.residex.report.controller;

import com.residex.report.dto.GenerateMonthlyReportRequest;
import com.residex.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(
        name = "Reports",
        description = "Monthly PDF Reports"
)
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    private final ReportService reportService;

    @Operation(
            summary = "Generate Monthly Student Report"
    )
    @PostMapping("/monthly")
    public ResponseEntity<byte[]> generateReport(

            @Valid
            @RequestBody
            GenerateMonthlyReportRequest request

    ) {

        byte[] pdf =
                reportService.generateMonthlyReport(

                        request.getStudentId(),

                        request.getMonth(),

                        request.getYear()

                );

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Monthly-Student-Report.pdf"
                )

                .contentType(
                        MediaType.APPLICATION_PDF
                )

                .body(pdf);

    }

}
package com.residex.report.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.residex.exception.ResourceNotFoundException;

import com.residex.feerecord.entity.FeeRecord;
import com.residex.feerecord.repository.FeeRecordRepository;

import com.residex.report.service.ReportService;

import com.residex.stayrecord.entity.StayRecord;
import com.residex.stayrecord.repository.StayRecordRepository;

import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl
        implements ReportService {

    private final StudentRepository studentRepository;

    private final StayRecordRepository stayRecordRepository;

    private final FeeRecordRepository feeRecordRepository;

    @Override
public byte[] generateMonthlyReport(

        Long studentId,

        Integer month,

        Integer year

) {

    Student student =
            studentRepository.findById(studentId)

                    .orElseThrow(() ->

                            new ResourceNotFoundException(
                                    "Student not found"
                            )

                    );

    StayRecord stayRecord =
            stayRecordRepository
                    .findByStudentIdAndMonthAndYear(

                            studentId,

                            month,

                            year

                    )

                    .orElseThrow(() ->

                            new ResourceNotFoundException(
                                    "Stay record not found"
                            )

                    );

    FeeRecord feeRecord =
            feeRecordRepository
                    .findByStudentIdAndMonthAndYear(

                            studentId,

                            month,

                            year

                    )

                    .orElseThrow(() ->

                            new ResourceNotFoundException(
                                    "Fee record not found"
                            )

                    );

            try {

                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream();

                Document document =
                        new Document(PageSize.A4);

                PdfWriter.getInstance(
                        document,
                        outputStream
                );

                document.open();

                   Font titleFont =
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD,
                                20
                        );

                Font headingFont =
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD,
                                14
                        );

                Font normalFont =
                        FontFactory.getFont(
                                FontFactory.HELVETICA,
                                11
                        );

                Font boldFont =
                        FontFactory.getFont(
                                FontFactory.HELVETICA_BOLD,
                                11
                        );

                   Paragraph title =
                        new Paragraph(

                                "RESIDEX HOSTEL MANAGEMENT SYSTEM",

                                titleFont

                        );

                title.setAlignment(Element.ALIGN_CENTER);

                document.add(title);

                Paragraph subTitle =
                        new Paragraph(

                                "Monthly Student Report",

                                headingFont

                        );

                subTitle.setAlignment(Element.ALIGN_CENTER);

                document.add(subTitle);

                document.add(new Paragraph(" "));

                PdfPTable studentTable =
                        new PdfPTable(2);

                studentTable.setWidthPercentage(100);

                    addRow(
                        studentTable,
                        "Student Name",
                        student.getUser().getName()
                );

                addRow(
                        studentTable,
                        "Register Number",
                        student.getRegisterNumber()
                );

                addRow(
                        studentTable,
                        "Department",
                        student.getDepartment().getName()
                );

                addRow(
                        studentTable,
                        "Year",
                        String.valueOf(
                                student.getYearOfStudy()
                        )
                );

                addRow(
                        studentTable,
                        "Residence",
                        student.getRoom()
                                .getResidence()
                                .getName()
                );

                addRow(
                        studentTable,
                        "Room",
                        student.getRoom()
                                .getRoomNumber()
                );

                document.add(studentTable);

                document.add(new Paragraph(" "));

                Paragraph attendanceHeading =
                new Paragraph(
                        "Attendance Summary",
                        headingFont
                );

        attendanceHeading.setSpacingAfter(10);

        document.add(attendanceHeading);

        PdfPTable attendanceTable =
                new PdfPTable(2);

        attendanceTable.setWidthPercentage(100);

        addRow(
                attendanceTable,
                "Total Days",
                String.valueOf(
                        stayRecord.getTotalDays()
                )
        );

        addRow(
                attendanceTable,
                "Stayed Days",
                String.valueOf(
                        stayRecord.getStayedDays()
                )
        );

        addRow(
                attendanceTable,
                "Approved Leave",
                String.valueOf(
                        stayRecord.getApprovedLeaveDays()
                )
        );

        addRow(
                attendanceTable,
                "Unauthorized Absence",
                String.valueOf(
                        stayRecord.getUnauthorizedAbsenceDays()
                )
        );

        document.add(attendanceTable);

        document.add(new Paragraph(" "));

        Paragraph feeHeading =
                new Paragraph(
                        "Fee Summary",
                        headingFont
                );

        feeHeading.setSpacingAfter(10);

        document.add(feeHeading);

        PdfPTable feeTable =
                new PdfPTable(2);

        feeTable.setWidthPercentage(100);

        addRow(
                feeTable,
                "Base Fee",
                "₹ " + feeRecord.getBaseFee()
        );

        addRow(
                feeTable,
                "Leave Deduction",
                "₹ " + feeRecord.getDeductions()
        );

        addRow(
                feeTable,
                "Penalty",
                "₹ " + feeRecord.getPenalties()
        );

        addRow(
                feeTable,
                "Final Amount",
                "₹ " + feeRecord.getFinalAmount()
        );

        addRow(
                feeTable,
                "Payment Status",
                feeRecord.getPaid()
                        ? "PAID"
                        : "UNPAID"
        );

        document.add(feeTable);

        document.add(new Paragraph(" "));

        Paragraph footer =
                new Paragraph(

                        "\n\n----------------------------------------------\n"

                                + "RESIDEX Hostel Management System\n\n"

                                + "Monthly Student Hostel Report\n\n"

                                + "Generated on : "

                                + LocalDateTime.now().format(

                                DateTimeFormatter.ofPattern(

                                        "dd MMM yyyy hh:mm a"

                                )

                        ),

                        normalFont

                );

        footer.setAlignment(Element.ALIGN_CENTER);

        document.add(footer);

            document.close();

        return outputStream.toByteArray();

        }

        catch (Exception e) {

        throw new RuntimeException(
                "Unable to generate PDF"
        );

        }

    }

   private void addRow(

        PdfPTable table,

        String key,

        String value

) {

    Font keyFont =
            FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    11
            );

    Font valueFont =
            FontFactory.getFont(
                    FontFactory.HELVETICA,
                    11
            );

    PdfPCell left =
            new PdfPCell(
                    new Phrase(key, keyFont)
            );

    PdfPCell right =
            new PdfPCell(
                    new Phrase(value, valueFont)
            );

    left.setPadding(8);

    right.setPadding(8);

    table.addCell(left);

    table.addCell(right);

}



}
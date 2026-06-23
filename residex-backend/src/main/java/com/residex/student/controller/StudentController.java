package com.residex.student.controller;

import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;
import com.residex.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Student Management",
        description = "Student operations and room allocation"
)

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor




public class StudentController {

    private final StudentService studentService;


    @Operation(
            summary = "Get all students"
    )
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }


    @Operation(
            summary = "Get student by ID"
    )
    @GetMapping("/{id}")
    public StudentResponse getStudent(
            @PathVariable Long id
    ) {
        return studentService.getStudent(id);
    }


    @Operation(
            summary = "Assign room to student"
    )
    @PutMapping("/{id}/assign-room")
    public StudentResponse assignRoom(
            @PathVariable Long id,
            @RequestBody AssignRoomRequest request
    ) {
        return studentService.assignRoom(
                id,
                request
        );
    }
}
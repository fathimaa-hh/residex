package com.residex.student.controller;

import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;
import com.residex.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(
            @PathVariable Long id
    ) {
        return studentService.getStudent(id);
    }

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
package com.residex.student.service;

import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudents();

    StudentResponse getStudent(Long studentId);

    StudentResponse assignRoom(
            Long studentId,
            AssignRoomRequest request
    );
}
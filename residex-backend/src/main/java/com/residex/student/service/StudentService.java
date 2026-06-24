package com.residex.student.service;

import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;
import org.springframework.data.domain.Page;

public interface StudentService {

    Page<StudentResponse> getAllStudents(
            int page,
            int size
    );

    StudentResponse getStudent(
            Long studentId
    );

    StudentResponse assignRoom(
            Long studentId,
            AssignRoomRequest request
    );
}
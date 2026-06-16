package com.residex.student.service.impl;

import com.residex.room.entity.Room;
import com.residex.room.repository.RoomRepository;
import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import com.residex.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudent(Long studentId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"
                                ));

        return mapToResponse(student);
    }

    @Override
    public StudentResponse assignRoom(
            Long studentId,
            AssignRoomRequest request
    ) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"
                                ));

        Room room =
                roomRepository.findById(
                        request.getRoomId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"
                        ));

        student.setRoom(room);

        studentRepository.save(student);

        return mapToResponse(student);
    }
    

    private StudentResponse mapToResponse(
            Student student
    ) {

        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getUser().getName())
                .email(student.getUser().getEmail())
                .registerNumber(student.getRegisterNumber())
                .department(
                        student.getDepartment() != null
                                ? student.getDepartment().getName()
                                : null
                )
                .roomNumber(
                        student.getRoom() != null
                                ? student.getRoom().getRoomNumber()
                                : null
                )
                .residence(
                        student.getRoom() != null && student.getRoom().getResidence() != null
                                ? student.getRoom().getResidence().getName()
                                : null
                )
                .yearOfStudy(
                        student.getYearOfStudy()
                )
                .build();
    }
}

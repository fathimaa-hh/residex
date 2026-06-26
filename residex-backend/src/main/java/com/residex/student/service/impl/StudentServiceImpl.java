package com.residex.student.service.impl;

import com.residex.exception.ResourceNotFoundException;
import com.residex.room.entity.Room;
import com.residex.room.repository.RoomRepository;
import com.residex.student.dto.AssignRoomRequest;
import com.residex.student.dto.StudentResponse;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import com.residex.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.residex.notification.service.NotificationService;
import com.residex.common.enums.NotificationType;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final NotificationService notificationService;

    @Override
        public Page<StudentResponse> getAllStudents(
                int page,
                int size
        ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("id")
                                .descending()
                );

        return studentRepository
                .findAll(pageable)
                .map(this::mapToResponse);
        }

    @Override
    public StudentResponse getStudent(Long studentId) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
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
                                new ResourceNotFoundException(
                                        "Student not found"
                                ));

        Room room =
                roomRepository.findById(
                        request.getRoomId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"
                        )
                        );

        student.setRoom(room);

        studentRepository.save(student);

        notificationService.createNotification(

                student.getId(),

                "Room Assigned",

                "You have been assigned Room "
                        + room.getRoomNumber()
                        + " in "
                        + room.getResidence().getName(),

                NotificationType.ROOM
        );

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

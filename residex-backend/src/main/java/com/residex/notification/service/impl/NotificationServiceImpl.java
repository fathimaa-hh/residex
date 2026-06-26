package com.residex.notification.service.impl;

import com.residex.common.enums.NotificationType;
import com.residex.exception.ResourceNotFoundException;
import com.residex.notification.dto.NotificationResponse;
import com.residex.notification.entity.Notification;
import com.residex.notification.repository.NotificationRepository;
import com.residex.notification.service.NotificationService;
import com.residex.student.entity.Student;
import com.residex.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final StudentRepository studentRepository;

    

    @Override
    public void createNotification(

            Long studentId,

            String title,

            String message,

            NotificationType notificationType
    ) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"
                                ));

        Notification notification = new Notification();

        notification.setStudent(student);

        notification.setTitle(title);

        notification.setMessage(message);

        notification.setNotificationType(notificationType);

        notification.setIsRead(false);

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> getStudentNotifications(

            Long studentId
    ) {

        return notificationRepository
                .findByStudentIdOrderByCreatedAtDesc(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void markAsRead(

            Long notificationId
    ) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found"
                                ));

        notification.setIsRead(true);

        notificationRepository.save(notification);
    }

    @Override
        public long getUnreadCount(
                Long studentId
        ) {

        return notificationRepository
                .countByStudentIdAndIsReadFalse(
                        studentId
                );
        }

    private NotificationResponse mapToResponse(

            Notification notification
    ) {

        return NotificationResponse.builder()

                .id(notification.getId())

                .title(notification.getTitle())

                .message(notification.getMessage())

                .notificationType(
                        notification.getNotificationType()
                )

                .isRead(notification.getIsRead())

                .createdAt(notification.getCreatedAt())

                .build();
    }

}
package com.residex.notification.repository;

import com.residex.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByStudentIdOrderByCreatedAtDesc(
            Long studentId
    );

    long countByStudentIdAndIsReadFalse(
            Long studentId
    );

}
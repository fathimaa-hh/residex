package com.residex.notification.service;

import com.residex.common.enums.NotificationType;
import com.residex.notification.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void createNotification(

            Long studentId,

            String title,

            String message,

            NotificationType notificationType
    );

    List<NotificationResponse> getStudentNotifications(

            Long studentId
    );

    void markAsRead(

            Long notificationId
    );
    long getUnreadCount(Long studentId);
}
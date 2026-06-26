package com.residex.notification.dto;

import com.residex.common.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private String title;

    private String message;

    private NotificationType notificationType;

    private Boolean isRead;

    private LocalDateTime createdAt;
}
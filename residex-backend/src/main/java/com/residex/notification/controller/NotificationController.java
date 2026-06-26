package com.residex.notification.controller;

import com.residex.notification.dto.NotificationResponse;
import com.residex.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(
        name = "Notifications",
        description = "Notification Management APIs"
)
@SecurityRequirement(name = "bearerAuth")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{studentId}")

    public List<NotificationResponse> getNotifications(

            @PathVariable Long studentId
    ) {

        return notificationService
                .getStudentNotifications(studentId);
    }

    @GetMapping("/{studentId}/unread-count")
        public long unreadCount(

                @PathVariable Long studentId
        ) {

        return notificationService
                .getUnreadCount(studentId);
        }

    @PutMapping("/{notificationId}/read")

    public String markRead(

            @PathVariable Long notificationId
    ) {

        notificationService.markAsRead(notificationId);

        return "Notification marked as read";
    }

    

}
package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.NotificationDTO;
import ru.domen.Notification;
import ru.services.NotificationService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getUserNotifications/")
    public List<NotificationDTO> getUserNotifications(@RequestParam Integer userId) {
        return NotificationDTO.getNotificationDTO(notificationService.getUserNotifications(userId));
    }

    @GetMapping("/getUserNotificationsSize/")
    public Integer getUserNotificationsSize(@RequestParam Integer userId) {
        return notificationService.getUserNotifications(userId).size();
    }

    @GetMapping("/getMessageNotifications/")
    public List<NotificationDTO> getMessageNotifications(@RequestParam Integer messageId) {
        return NotificationDTO.getNotificationDTO(notificationService.getMessageNotifications(messageId));
    }

    @DeleteMapping("/cleanUserNotifications/")
    public void cleanUserNotification(@RequestParam Integer userId, @RequestParam Integer dialogId) {
        notificationService.deleteUserNotification(userId,dialogId);
    }

    @GetMapping("notifications/getUserDialogNotifications")
    public List<NotificationDTO> getUserDialogNotifications(@RequestParam Integer userId, @RequestParam Integer dialogId) {
        return NotificationDTO.getNotificationDTO(notificationService.getUserDialogNotifications(userId,dialogId));
    }

}

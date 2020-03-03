package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.domen.Notification;
import ru.services.NotificationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getUserNotifications/")
    public List<Notification> getUserNotifications(@RequestParam Integer userId) {
        return notificationService.getUserNotifications(userId);
    }

    @GetMapping("/getMessageNotifications/")
    public List<Notification> getMessageNotifications(@RequestParam Integer messageId) {
        return notificationService.getMessageNotifications(messageId);
    }
}

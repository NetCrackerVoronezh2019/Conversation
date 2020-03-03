package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.DTO.NotificationDTO;
import ru.domen.Notification;
import ru.services.NotificationService;

import java.util.ArrayList;
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
    public List<NotificationDTO> getMessageNotifications(@RequestParam Integer messageId) {
        List<Notification> notifications= notificationService.getMessageNotifications(messageId);
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (int i = 0; i < notifications.size(); i++) {
            notificationDTOList.add(NotificationDTO.getNotificationDTO(notifications.get(i)));
        }
        return notificationDTOList;
    }
}

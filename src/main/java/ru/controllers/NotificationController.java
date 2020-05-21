package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.NotificationDTO;
import ru.domen.Message;
import ru.domen.Notification;
import ru.services.MessageService;
import ru.services.NotificationService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private MessageService messageService;

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
        List<Message> messages = notificationService.getUserDialogNotifications(userId,dialogId).stream().map(Notification::getMessage).collect(Collectors.toList());
        for (Message message :
                messages) {
            message.setReadBySomebodey(true);
            messageService.addMessage(message);
        }
        notificationService.deleteUserNotification(userId,dialogId);
    }

    @GetMapping("notifications/getUserDialogNotifications")
    public List<NotificationDTO> getUserDialogNotifications(@RequestParam Integer userId, @RequestParam Integer dialogId) {
        return NotificationDTO.getNotificationDTO(notificationService.getUserDialogNotifications(userId,dialogId).stream().sorted(Comparator.comparing(Notification::getDate)).collect(Collectors.toList()));
    }

}

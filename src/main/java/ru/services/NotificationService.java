package ru.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Message;
import ru.domen.Notification;
import ru.repos.NotificationRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public List<Notification> addNotification(Message message) {
        List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < message.getDialog().getUsers().size(); i++) {
            if (message.getSender().getUserId() != message.getDialog().getUsers().get(i).getUserId()) {
                Notification notification = new Notification();
                notification.setDate(new Date());
                notification.setMessage(message);
                notification.setUser(message.getDialog().getUsers().get(i));
                notification.setIsread(false);
                notifications.add(notification);
            }
        }
        for (int i = 0; i < notifications.size(); i++) {
            notificationRepository.save(notifications.get(i));
        }
        return notifications;
    }

    @Transactional
    public void deleteUserNotification(Integer userId, Integer dialogId) {
        notificationRepository.deleteByMessageDialogDialogIdAndUserUserId(dialogId,userId);
    }

    public List<Notification> getUserNotifications(Integer userId) {
        return notificationRepository.findByUserUserId(userId);
    }
    public List<Notification> getMessageNotifications(Integer userId) {
        return notificationRepository.findByMessageMessageId(userId);
    }
}

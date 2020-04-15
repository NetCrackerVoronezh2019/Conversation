package ru.DTO;

import ru.domen.Message;
import ru.domen.Notification;
import ru.domen.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationDTO {
    private Integer notificationId;
    private Integer messageId;
    private Integer userId;
    private String senderName;
    private Date date;
    private boolean isread;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public static NotificationDTO getNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDate(notification.getDate());
        notificationDTO.setIsread(notification.isIsread());
        notificationDTO.setMessageId(notification.getMessage().getMessageId());
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setSenderName(notification.getMessage().getSender().getName());
        notificationDTO.setUserId(notification.getUser().getUserId());
        return notificationDTO;
    }

    public static List<NotificationDTO> getNotificationDTO(List<Notification> notifications) {
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (int i = 0; i < notifications.size(); i++) {
            notificationDTOList.add(NotificationDTO.getNotificationDTO(notifications.get(i)));
        }
        return notificationDTOList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }
}

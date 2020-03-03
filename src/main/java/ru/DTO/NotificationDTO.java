package ru.DTO;

import ru.domen.Message;
import ru.domen.Notification;
import ru.domen.User;

import javax.persistence.*;
import java.util.Date;

public class NotificationDTO {
    private Integer notificationId;
    private Integer messageId;
    private String userName;
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
        notificationDTO.setUserName(notification.getUser().getName());
        return notificationDTO;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

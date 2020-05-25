package ru.domen;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MESSAGEID")
    private Integer messageId;
    @Column(name="TEXT")
    private String text;
    @Column(name="DATEOFSENDING")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "message")
    private List<Notification> notifications = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dialogId")
    private Dialog dialog;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User sender;
    @Column(name="isModified")
    private boolean isModified;
    @Column(name="isReadBySomebody")
    private boolean isReadBySomebodey;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "message")
    private List<MessageFile> files = new ArrayList<>();

    public Message(String text, Dialog dialog, User sender) {
        this.text = text;
        this.date = LocalDateTime.now();
        this.dialog = dialog;
        this.sender = sender;
        isModified = false;
    }

    public boolean isReadBySomebodey() {
        return isReadBySomebodey;
    }

    public void setReadBySomebodey(boolean readBySomebodey) {
        isReadBySomebodey = readBySomebodey;
    }

    public List<MessageFile> getFiles() {
        return files;
    }

    public void setFiles(List<MessageFile> files) {
        this.files = files;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }



    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }
}

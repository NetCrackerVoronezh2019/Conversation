package ru.domen;

import javax.persistence.*;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
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

    public Message(String text, Dialog dialog, User sender) {
        this.text = text;
        this.date = new Date();
        this.dialog = dialog;
        this.sender = sender;
        isModified = false;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }
}

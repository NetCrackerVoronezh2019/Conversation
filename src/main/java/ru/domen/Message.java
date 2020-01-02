package ru.domen;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Massages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    private String text;
    private Date date;
    private Long dialogId;
    private Long senderId;
    private boolean isModificated;

    public Message(String text, Date date, Long dialogId, Long senderId) {
        this.text = text;
        this.date = date;
        this.dialogId = dialogId;
        this.senderId = senderId;
        this.isModificated = false;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
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

    public Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId = dialogId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public boolean isModificated() {
        return isModificated;
    }

    public void setModificated(boolean modificated) {
        isModificated = modificated;
    }
}

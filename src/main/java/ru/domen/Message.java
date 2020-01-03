package ru.domen;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Massages")
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
    @Column(name="DIALOGID")
    private Integer dialogId;
    @Column(name="senderID")
    private Integer senderId;
    @Column(name="isModificated")
    private boolean isModificated;

    public Message(String text, Date date, Integer dialogId, Integer senderId) {
        this.text = text;
        this.date = date;
        this.dialogId = dialogId;
        this.senderId = senderId;
        this.isModificated = false;
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

    public Integer getDialogId() {
        return dialogId;
    }

    public void setDialogId(Integer dialogId) {
        this.dialogId = dialogId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public boolean isModificated() {
        return isModificated;
    }

    public void setModificated(boolean modificated) {
        isModificated = modificated;
    }
}

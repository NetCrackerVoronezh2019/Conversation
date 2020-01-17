package ru.DTO;

import ru.domen.Message;

import java.util.Date;

public class MessageDTO {
    private Integer messageId;
    private String text;
    private Date date;
    private Integer dialog;
    private UserDTO sender;
    private boolean isModified;

    public MessageDTO(Message message) {
        messageId = message.getMessageId();
        text = message.getText();
        date = message.getDate();
        dialog = message.getDialog().getDialogId();
        isModified = message.isModified();
    }

    public static MessageDTO getMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO(message);
        messageDTO.sender =new UserDTO(message.getSender());
        return messageDTO;
    }

    public MessageDTO() {
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
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

    public Integer getDialog() {
        return dialog;
    }

    public void setDialog(Integer dialog) {
        this.dialog = dialog;
    }

}

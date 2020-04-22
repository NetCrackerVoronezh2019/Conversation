package ru.DTO;

import ru.domen.Message;
import ru.domen.MessageFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDTO {
    private Integer messageId;
    private String text;
    private Date date;
    private Integer dialog;
    private UserDTO sender;
    private boolean isModified;
    private List<String> files = new ArrayList<>();
    private List<String> names = new ArrayList<>();

    public MessageDTO(Message message) {
        messageId = message.getMessageId();
        text = message.getText();
        date = message.getDate();
        dialog = message.getDialog().getDialogId();
        isModified = message.isModified();
        for (MessageFile file :
                message.getFiles()) {
            this.files.add(file.getFile());
            this.names.add(file.getName());
        }
    }

    public static MessageDTO getMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO(message);
        messageDTO.sender =new UserDTO(message.getSender());
        return messageDTO;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
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

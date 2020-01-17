package ru.DTO;

import ru.domen.Message;

import java.util.ArrayList;
import java.util.List;
import ru.domen.Dialog;
import ru.domen.User;

public class UserDTO {
    private Integer userId;
    private String name;
    private String status;
    private String roleName;
    private List<DialogDTO> dialogs;
    private List<MessageDTO> messages;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.status = user.getStatus();
        this.roleName = user.getRoleName();
    }

    public static UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user);
        userDTO.dialogs = new ArrayList<>();
        userDTO.messages = new ArrayList<>();
        for (Dialog dialog:
             user.getDialogs()) {
            userDTO.dialogs.add(new DialogDTO(dialog));
        }
        for (Message message:
                user.getMessages()) {
            userDTO.messages.add(new MessageDTO(message));
        }
        return userDTO;
    }

    public UserDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<DialogDTO> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<DialogDTO> dialogs) {
        this.dialogs = dialogs;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}

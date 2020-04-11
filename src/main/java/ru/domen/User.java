package ru.domen;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    @Column(name="userid")
    private Integer userId;
    @Column(name="name")
    private String name;
    @ManyToMany(mappedBy = "users")
    private List<Dialog> dialogs = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public User() {
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User(String name, String status, String roleName) {
        this.name = name;
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

}

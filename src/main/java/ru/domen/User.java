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
    @Column(name="status")
    private String status;
    @Column(name="rolename")
    private String roleName;
    @ManyToMany(mappedBy = "users")
    private List<Dialog> dialogs = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();



    public User() {
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
        this.status = status;
        this.roleName = roleName;
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
}

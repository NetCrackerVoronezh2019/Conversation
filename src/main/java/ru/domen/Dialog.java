package ru.domen;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import ru.services.UserService;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Dialogs")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dialogID")
    private Integer dialogId;
    @Column(name="name")
    private String name;
    @Column(name="creationDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDate;
    @Column(name="CreatorID")
    private Integer creatorId;
    @Column(name="image")
    private String image;

    @ManyToMany
    @JoinTable(name = "UserDialog",
            joinColumns = @JoinColumn(name = "dialogId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "dialog")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeId")
    private DialogType type;

    public DialogType getType() {
        return type;
    }

    public void setType(DialogType type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Dialog(String name, LocalDateTime creationDate, Integer creatorId) {
        this.name = name;
        this.creationDate = creationDate;
        this.creatorId = creatorId;
    }

    public Dialog() {
    }

    public  Integer getDialogId() {
        return dialogId;
    }

    public void setDialogId(Integer dialogId) {
        this.dialogId = dialogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}

package ru.domen;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Dialogs")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dialogID")
    private Integer dialogId;
    @Column(name="name")
    private String name;
    @Column(name="CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreationDate;
    @Column(name="CreatorID")
    private Integer CreatorId;

    public Dialog(String name, Date creationDate, Integer creatorId) {
        this.name = name;
        CreationDate = creationDate;
        CreatorId = creatorId;
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

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public Integer getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(Integer creatorId) {
        CreatorId = creatorId;
    }
}

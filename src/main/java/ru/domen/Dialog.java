package ru.domen;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Dialogs")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dialogID")
    private Long dialogId;
    @Column(name="name")
    private String name;
    @Column(name="CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreationDate;
    @Column(name="CreatorID")
    private Long CreatorId;

    public Dialog(String name, Date creationDate, Long creatorId) {
        this.name = name;
        CreationDate = creationDate;
        CreatorId = creatorId;
    }

    public  Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
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

    public Long getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(Long creatorId) {
        CreatorId = creatorId;
    }
}

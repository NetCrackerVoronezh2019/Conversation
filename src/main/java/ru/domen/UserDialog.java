package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "UserDialog")
public class UserDialog {

    @Id
    @Column(name="USERID")
    private Integer userId;

    @Column(name="DIALOGID")
    private Integer dialogId;

    public UserDialog(Integer userId, Integer dialogId) {
        this.userId = userId;
        this.dialogId = dialogId;
    }

    public UserDialog() {
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDialogId() {
        return dialogId;
    }
}

package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "UserDialog")
public class UserDialog {

    @Id
    private Long userId;

    private Long dialogId;

    public UserDialog(Long userId, Long dialogId) {
        this.userId = userId;
        this.dialogId = dialogId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDialogId() {
        return dialogId;
    }
}

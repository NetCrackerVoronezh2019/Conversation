package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USERID")
    private Long UserId;
    @Column(name="Name")
    private String name;
    @Column(name="status")
    private String status;
    @Column(name="roleName")
    private String roleName;

    public User(String name, String status, String roleName) {
        this.name = name;
        this.status = status;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
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

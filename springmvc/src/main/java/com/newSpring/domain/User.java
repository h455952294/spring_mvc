package com.newSpring.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/4.
 */
@Entity
@Table(name = "SYS_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SYS_USER_ID")
    private Integer userId;
    @Column(name = "SYS_USER_NAME")
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.demo.entity;

import java.io.Serializable;

/**
 * <p> @Title UserEntity
 * <p> @Description Redis测试 - 序列化实体类
 *
 * @author ACGkaka
 * @date 2020/12/9 15:21
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5367222612288420726L;

    private Long id;
    private String userName;
    private String userSex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}

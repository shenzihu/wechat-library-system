/*
 * User.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2017-05-10 Created
 */
package org.bookmate.entities;

import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author Tiger
 */
public class User {
    private String phone;
    //用户id
    private Integer userId;
    //用户名
    private String userUsername;
    private String realName;
    private String department;
    //用户密码
    private String userPassword;
    //创建时间
    private Date userCreateTime;

    private List<Book> orderBooks;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Book> getOrderBooks() {
        return orderBooks;
    }

    public void setOrderBooks(List<Book> orderBooks) {
        this.orderBooks = orderBooks;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

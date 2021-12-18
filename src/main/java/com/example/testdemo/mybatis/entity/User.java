package com.example.testdemo.mybatis.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    private String userId;
    
    @NotBlank(message = "用户名称不能为空")
    private String userName;
    
    @NotBlank(message = "用户密码不能为空")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
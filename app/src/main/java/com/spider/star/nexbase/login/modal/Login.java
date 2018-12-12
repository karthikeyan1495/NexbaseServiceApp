package com.spider.star.nexbase.login.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("password")
    @Expose
    String password;


}

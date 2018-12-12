package com.spider.star.nexbase.customerdetails.modal;

import com.google.gson.annotations.SerializedName;

public class CustomerDetails {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("address")
    String address;

    @SerializedName("mobile")
    String mobile;



}

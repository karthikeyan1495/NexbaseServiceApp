package com.spider.star.nexbase.servicerequest.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationList implements Serializable {


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }

    @SerializedName("message")
    String message;

    @SerializedName("ticket_number")
    String ticket_number;

    @SerializedName("id")
    String id;

    @SerializedName("customer_name")
    String customer_name;

    @SerializedName("address")
    String address;

    @SerializedName("mobile")
    String mobile;

    @SerializedName("problem_reported")
    String problem_reported;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public String getProblem_reported() {
        return problem_reported;
    }

    public void setProblem_reported(String problem_reported) {
        this.problem_reported = problem_reported;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @SerializedName("latitude")
    String latitude;

    @SerializedName("longitude")
    String longitude;


}

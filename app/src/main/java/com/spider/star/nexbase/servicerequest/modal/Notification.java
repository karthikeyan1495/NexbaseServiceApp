package com.spider.star.nexbase.servicerequest.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification {

    public List<NotificationList> getNotificationLists() {
        return notificationLists;
    }

    public void setNotificationLists(List<NotificationList> notificationLists) {
        this.notificationLists = notificationLists;
    }

    @SerializedName("data")
    List<NotificationList> notificationLists;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SerializedName("status")
    int status;

}

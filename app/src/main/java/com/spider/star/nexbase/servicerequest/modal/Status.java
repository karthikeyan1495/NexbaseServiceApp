package com.spider.star.nexbase.servicerequest.modal;

import com.google.gson.annotations.SerializedName;

public class Status {

    public String getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    @SerializedName("ticket_status")
    String ticket_status;


    @SerializedName("ticket_id")
    String ticket_id;


}

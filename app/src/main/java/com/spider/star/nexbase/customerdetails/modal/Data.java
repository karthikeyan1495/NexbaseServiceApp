package com.spider.star.nexbase.customerdetails.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SerializedName("status")
    int status;


    public List<CustomerDetails> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<CustomerDetails> customerDetails) {
        this.customerDetails = customerDetails;
    }

    @SerializedName("data")
    List<CustomerDetails>customerDetails;

}

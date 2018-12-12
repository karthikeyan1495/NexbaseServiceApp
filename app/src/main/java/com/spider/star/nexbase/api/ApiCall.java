package com.spider.star.nexbase.api;


import com.spider.star.nexbase.customerdetails.modal.Data;
import com.spider.star.nexbase.login.modal.Login;
import com.spider.star.nexbase.servicerequest.modal.Notification;
import com.spider.star.nexbase.servicerequest.modal.Status;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCall {

    @POST("engineer_login")
    Observable<Response<GeneralResponse>> login(@Body Login login);

    @GET("get_client_details")
    Observable<Response<Data>> getCustomerDetails();

    @POST("notifications_service_engineer")
    Observable<Response<Notification>> getNotification(@Body Login login);

    @POST("ticket_status")
    Observable<Response<GeneralResponse>> ticketStatus(@Body Status status);








}

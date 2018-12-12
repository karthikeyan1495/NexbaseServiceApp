package com.spider.star.nexbase.servicerequest.adaptor;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.api.APIConfiguration;
import com.spider.star.nexbase.api.APIErrorHandler;
import com.spider.star.nexbase.api.ApiCall;
import com.spider.star.nexbase.api.GeneralResponse;
import com.spider.star.nexbase.customerdetails.CustomerDetailsActivity;
import com.spider.star.nexbase.servicerequest.modal.NotificationList;
import com.spider.star.nexbase.servicerequest.modal.Status;
import com.spider.star.nexbase.utils.InternetChecker;
import com.spider.star.nexbase.utils.MyProgressDialog;
import com.spider.star.nexbase.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StatusItemChanges extends java.util.Observable {

    Activity activity;
    NotificationList notificationList;
    MyProgressDialog myProgressDialog;
    Status status;

    public StatusItemChanges(Activity activity, NotificationList notificationList) {

        this.activity=activity;
        this.notificationList=notificationList;
        status=new Status();
        myProgressDialog=new MyProgressDialog();


    }

    public  void onClickAccept(View view){

      //  CallStatusApi("Accepted");

       activity.startActivity(new Intent(activity,CustomerDetailsActivity.class).putExtra("notificationlist",notificationList));


    }

    private void CallStatusApi(String statuss) {

        status.setTicket_status(statuss);
        status.setTicket_id(notificationList.getId());

        if (InternetChecker.getInstance().isReachable()) {
            myProgressDialog.showDialog(activity);

            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.ticketStatus(status);

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        GeneralResponse responsive=responses.body();

                        if (responsive.getCode() == 200) {

                            Toast.makeText(activity,""+responsive.getMessage(),Toast.LENGTH_LONG).show();
                            setChanged();
                            notifyObservers(notificationList);
                            activity.startActivity(new Intent(activity,CustomerDetailsActivity.class).putExtra("notificationlist",notificationList));

                        } else {
                            if (responses.body() != null) {
                                APIErrorHandler.getInstance().errorHandler(activity, responses.code(), responses.body().getMessage());
                            } else {
                                APIErrorHandler.getInstance().errorHandler(activity, responses.code(), responses.errorBody().string());
                            }
                        }
                    }, throwable -> {
                        myProgressDialog.dismissDialog();
                        MySnackBar.getInstance().showNagativeSnackBar(activity, activity.getString(R.string.something_went_wrong_while_retrieving_information));

                    });

        } else {
            MySnackBar.getInstance().showNagativeSnackBar(activity, activity.getString(R.string.no_internet));
        }

    }

    public void onClickDecline(View view){

    //   CallStatusApi("Rejected");
    }

}

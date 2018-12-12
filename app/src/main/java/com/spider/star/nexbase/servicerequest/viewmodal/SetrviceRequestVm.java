package com.spider.star.nexbase.servicerequest.viewmodal;

import android.app.Activity;


import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.api.APIConfiguration;
import com.spider.star.nexbase.api.APIErrorHandler;
import com.spider.star.nexbase.api.ApiCall;


import com.spider.star.nexbase.databinding.ServiceRequestBinding;
import com.spider.star.nexbase.login.modal.Login;

import com.spider.star.nexbase.servicerequest.adaptor.ServiceRequestAdaptor;
import com.spider.star.nexbase.servicerequest.modal.Notification;
import com.spider.star.nexbase.servicerequest.modal.NotificationList;
import com.spider.star.nexbase.shardprefeence.MySession;
import com.spider.star.nexbase.utils.InternetChecker;
import com.spider.star.nexbase.utils.MyProgressDialog;
import com.spider.star.nexbase.utils.MySnackBar;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class SetrviceRequestVm {

    Activity activity;
    ServiceRequestBinding binding;
    MyProgressDialog myProgressDialog;
    Login login;
    String servicerequest[] = {"Mr.Raj has request for service.", "Mr.Ram has request for service."};

    public SetrviceRequestVm(Activity activity, ServiceRequestBinding binding) {

        this.activity = activity;
        this.binding = binding;
        login = new Login();
        myProgressDialog = new MyProgressDialog();

       // setupRecycleView(binding);
        CallNotificationCall();


    }

    private void CallNotificationCall() {

        login.setUsername(MySession.getInstance(activity).getUserName());

        if (InternetChecker.getInstance().isReachable()) {
            myProgressDialog.showDialog(activity);

            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<Notification>> observable = api.getNotification(login);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        Notification notification=responses.body();

                        if (notification.getStatus() == 200) {

                            List<NotificationList> lists=responses.body().getNotificationLists();

                            setupRecycleView(binding,lists);

                        } else {
                            if (responses.body() != null) {
                                // APIErrorHandler.getInstance().errorHandler(activity, responses.code(), responses.body().getMessage());
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

    private void setupRecycleView(ServiceRequestBinding binding, List<NotificationList> lists) {

        binding.recycleView.setLayoutManager(new LinearLayoutManager(activity));

        ServiceRequestAdaptor adaptor = new ServiceRequestAdaptor(activity, lists);

        binding.recycleView.setAdapter(adaptor);

    }

    public void OnClickBackPress(View view){

        activity.finish();


    }
}

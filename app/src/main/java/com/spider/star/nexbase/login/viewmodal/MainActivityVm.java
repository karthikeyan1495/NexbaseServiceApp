package com.spider.star.nexbase.login.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.spider.star.nexbase.api.APIConfiguration;
import com.spider.star.nexbase.api.APIErrorHandler;
import com.spider.star.nexbase.api.ApiCall;
import com.spider.star.nexbase.api.GeneralResponse;
import com.spider.star.nexbase.databinding.ActivityMainBinding;
import com.spider.star.nexbase.login.modal.Login;
import com.spider.star.nexbase.servicerequest.ServiceRequestActivity;
import com.spider.star.nexbase.shardprefeence.MySession;
import com.spider.star.nexbase.utils.InternetChecker;
import com.spider.star.nexbase.utils.MyProgressDialog;
import com.spider.star.nexbase.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import com.spider.star.nexbase.R;


public class MainActivityVm {

    Activity activity;
    ActivityMainBinding binding;
    MyProgressDialog myProgressDialog;
    Login login;
    public MainActivityVm(Activity activity, ActivityMainBinding binding, Login login) {

        this.activity=activity;
        this.binding=binding;
        this.login=login;

        myProgressDialog=new MyProgressDialog();

    }

    public void onClickLogin(View view) {

        if(binding.username.getText().toString().trim().length() == 0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the username");

        }else if(binding.password.getText().toString().trim().length()==0){

            MySnackBar.getInstance().showNagativeSnackBar(activity,"Please enter the password");

        }else {

            CallApi();
        }



    }

    private void CallApi() {

        if (InternetChecker.getInstance().isReachable()) {
            myProgressDialog.showDialog(activity);

            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<GeneralResponse>> observable = api.login(login);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();
                        GeneralResponse generalResponse=responses.body();
                        if (generalResponse.getCode() == 200) {

                            MySession.getInstance(activity).saveUserName(login.getUsername());
                            MySession.getInstance(activity).saveLoginStatus(true);
                            Toast.makeText(activity,""+generalResponse.getMessage(),Toast.LENGTH_LONG).show();
                            activity.startActivity(new Intent(activity,ServiceRequestActivity.class));
                            activity.finishAffinity();

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
}

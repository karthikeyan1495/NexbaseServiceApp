package com.spider.star.nexbase.customerdetails.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.spider.star.nexbase.MapsActivity;
import com.spider.star.nexbase.R;
import com.spider.star.nexbase.api.APIConfiguration;
import com.spider.star.nexbase.api.APIErrorHandler;
import com.spider.star.nexbase.api.ApiCall;
import com.spider.star.nexbase.customerdetails.modal.Data;
import com.spider.star.nexbase.databinding.CustomerDetailsBinding;
import com.spider.star.nexbase.servicestatus.ServiceStatusActivity;
import com.spider.star.nexbase.utils.InternetChecker;
import com.spider.star.nexbase.utils.MyProgressDialog;
import com.spider.star.nexbase.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CustomerDetailsVm {

    Activity activity;
    CustomerDetailsBinding binding;
    MyProgressDialog myProgressDialog;
    String problem_report;
    String id;
    String latitude;
    String langitude;


    public CustomerDetailsVm(Activity activity, CustomerDetailsBinding binding, String problem_reported, String id,String latitude,String langitude) {

        this.activity=activity;
        this.binding=binding;
        this.problem_report=problem_reported;
        this.latitude=latitude;
        this.langitude=langitude;
        this.id=id;

        myProgressDialog=new MyProgressDialog();


      //  CustomerApiCall(binding);

    }

    private void CustomerApiCall(CustomerDetailsBinding binding) {

        if (InternetChecker.getInstance().isReachable()) {
            myProgressDialog.showDialog(activity);

            ApiCall api = APIConfiguration.getInstance().createService(ApiCall.class);
            Observable<Response<Data>> observable = api.getCustomerDetails();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responses -> {
                        myProgressDialog.dismissDialog();

                        if (responses.code() == 200) {

                            Data data=responses.body();

                            binding.name.setText(data.getCustomerDetails().get(0).getName());

                            binding.emailId.setText(data.getCustomerDetails().get(0).getEmail());

                            binding.mobileNo.setText(data.getCustomerDetails().get(0).getMobile());
                            binding.address.setText(data.getCustomerDetails().get(0).getAddress());

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

    public void onClickUpdateStatus(View view){

        activity.startActivity(new Intent(activity,ServiceStatusActivity.class).putExtra("problem_report",problem_report).putExtra("id",id));

    }


    public void OnClickBackPress(View view){

        activity.finish();

    }

    public void onClickgetDirction(View view){

        activity.startActivity(new Intent(activity,MapsActivity.class).putExtra("latitude",latitude).putExtra("langitude",langitude));

    }

}

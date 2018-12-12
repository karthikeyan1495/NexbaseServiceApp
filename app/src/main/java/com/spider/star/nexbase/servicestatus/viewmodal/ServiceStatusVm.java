package com.spider.star.nexbase.servicestatus.viewmodal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.api.APIConfiguration;
import com.spider.star.nexbase.api.APIErrorHandler;
import com.spider.star.nexbase.api.ApiCall;
import com.spider.star.nexbase.api.GeneralResponse;
import com.spider.star.nexbase.customerdetails.CustomerDetailsActivity;
import com.spider.star.nexbase.databinding.ServiceStatusBinding;
import com.spider.star.nexbase.servicerequest.modal.Status;
import com.spider.star.nexbase.servicestatusupdate.ServiceStatusUpdate;
import com.spider.star.nexbase.utils.InternetChecker;
import com.spider.star.nexbase.utils.MyProgressDialog;
import com.spider.star.nexbase.utils.MySnackBar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ServiceStatusVm {

    Activity activity;
    ServiceStatusBinding binding;
    String first_Level="";
    MyProgressDialog myProgressDialog;
    String id;
    Status status;




    public ServiceStatusVm(Activity activity, ServiceStatusBinding binding, String id) {

        this.activity = activity;
        this.binding = binding;
        this.id=id;
        status=new Status();
        myProgressDialog=new MyProgressDialog();
        spinnerloadingData();

    }

    private void spinnerloadingData() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.status_arrays, R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.status.setAdapter(adapter);


        binding.status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                first_Level = parent.getItemAtPosition(position).toString();

                if(binding.firstUpdate.getText().toString().trim().length()==0){

                    binding.firstUpdate.setText(first_Level);
                }
                else {

                    binding.secondUpdate.setText(first_Level);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClicksubmit(View view) {

        status.setTicket_status(first_Level);
        status.setTicket_id(id);

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

                            Toast.makeText(activity,responsive.getMessage(),Toast.LENGTH_LONG).show();

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



        //activity.startActivity(new Intent(activity, ServiceStatusUpdate.class));
    }

    public void OnClickbackPress(View view) {

        activity.finish();
    }
}

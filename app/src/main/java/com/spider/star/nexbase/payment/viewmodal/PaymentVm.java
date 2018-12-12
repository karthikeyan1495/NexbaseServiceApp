package com.spider.star.nexbase.payment.viewmodal;

import android.app.Activity;
import android.view.View;

import com.spider.star.nexbase.databinding.ActivityPaymentBinding;


public class PaymentVm {
    Activity activity;
    ActivityPaymentBinding binding;

    public PaymentVm(Activity activity, ActivityPaymentBinding binding) {

        this.activity=activity;
        this.binding=binding;

    }

    public  void  OnClickbackPress(View view){

        activity.finish();

    }
}

package com.spider.star.nexbase.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;


import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ActivityPaymentBinding;
import com.spider.star.nexbase.payment.viewmodal.PaymentVm;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    PaymentVm paymentVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this, R.layout.activity_payment);
        paymentVm=new PaymentVm(this,binding);
        binding.setPaymentVm(paymentVm);

        binding.invoices.setText(Html.fromHtml("<p><u>Invoice</u></p>"));



    }
}

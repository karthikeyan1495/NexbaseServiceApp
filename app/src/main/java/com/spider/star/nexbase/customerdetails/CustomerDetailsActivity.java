package com.spider.star.nexbase.customerdetails;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.customerdetails.viewmodal.CustomerDetailsVm;
import com.spider.star.nexbase.databinding.CustomerDetailsBinding;
import com.spider.star.nexbase.servicerequest.modal.NotificationList;

public class CustomerDetailsActivity extends AppCompatActivity {

    CustomerDetailsBinding binding;

    CustomerDetailsVm customerDetailsVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);
                return;
            }
        }


    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this, R.layout.customer_details);

        NotificationList notificationList= (NotificationList) getIntent().getSerializableExtra("notificationlist");
        binding.name.setText(notificationList.getCustomer_name());
        binding.emailId.setText("Nathan@gmail.com");
        binding.compliant.setText(notificationList.getProblem_reported());
        binding.mobileNo.setText(notificationList.getMobile());
        binding.address.setText(notificationList.getAddress());

        customerDetailsVm=new CustomerDetailsVm(this,binding,notificationList.getProblem_reported(),notificationList.getId(),notificationList.getLatitude(),notificationList.getLongitude());
        binding.setCustomerDetailsVm(customerDetailsVm);

    }
}

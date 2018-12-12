package com.spider.star.nexbase.servicestatus;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ServiceStatusBinding;
import com.spider.star.nexbase.servicestatus.viewmodal.ServiceStatusVm;

public class ServiceStatusActivity extends AppCompatActivity {

    ServiceStatusBinding binding;
    ServiceStatusVm serviceStatusVm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();
    }

    private void bindview() {


        Bundle bundle=getIntent().getExtras();

        String problem_Report=bundle.getString("problem_report");
        String id=bundle.getString("id");

        binding=DataBindingUtil.setContentView(this, R.layout.service_status);
        binding.problemReport.setText(problem_Report);
        serviceStatusVm=new ServiceStatusVm(this,binding,id);
        binding.setServiceStatusVm(serviceStatusVm);
    }
}

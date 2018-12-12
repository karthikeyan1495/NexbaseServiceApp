package com.spider.star.nexbase.servicestatusupdate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ServicestatusUpdateBinding;

public class ServiceStatusUpdate extends AppCompatActivity {

    ServicestatusUpdateBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();
    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this, R.layout.servicestatus_update);

        binding.backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}

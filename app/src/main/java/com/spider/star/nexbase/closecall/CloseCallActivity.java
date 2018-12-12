package com.spider.star.nexbase.closecall;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ClosecallBinding;

public class CloseCallActivity extends AppCompatActivity {

    ClosecallBinding binding;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this, R.layout.closecall);


    }
}

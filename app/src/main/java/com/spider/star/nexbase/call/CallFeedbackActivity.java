package com.spider.star.nexbase.call;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.call.viewmodal.CallFeedbackVm;
import com.spider.star.nexbase.databinding.CallfeedbackBinding;

public class CallFeedbackActivity extends AppCompatActivity {

    CallfeedbackBinding binding;
    CallFeedbackVm callFeedbackVm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.callfeedback);
        callFeedbackVm=new CallFeedbackVm(this,binding);
        binding.setCallfeedbackVm(callFeedbackVm);
    }

}

package com.spider.star.nexbase.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ActivityMainBinding;
import com.spider.star.nexbase.login.modal.Login;
import com.spider.star.nexbase.login.viewmodal.MainActivityVm;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainActivityVm mainActivityVm;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        bindview();

    }

    private void bindview() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        login = new Login();
        mainActivityVm = new MainActivityVm(this, binding, login);
        binding.setMainActivityVm(mainActivityVm);
        binding.setLogin(login);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

package com.spider.star.nexbase.servicerequest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.spider.star.nexbase.R;
import com.spider.star.nexbase.databinding.ServiceRequestBinding;
import com.spider.star.nexbase.login.MainActivity;
import com.spider.star.nexbase.servicerequest.viewmodal.SetrviceRequestVm;
import com.spider.star.nexbase.shardprefeence.MySession;

public class ServiceRequestActivity extends AppCompatActivity {

    SetrviceRequestVm setrviceRequestVm;

    ServiceRequestBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindview();

    }

    private void bindview() {

        binding=DataBindingUtil.setContentView(this,R.layout.service_request);
        setrviceRequestVm=new SetrviceRequestVm(this,binding);
        binding.setServiceRequestVm(setrviceRequestVm);


    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

   //    Toast.makeText(getApplicationContext(), "back_press", Toast.LENGTH_LONG).show();


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        TextView logout=dialogView.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                MySession.getInstance(ServiceRequestActivity.this).clearUserdata();
                startActivity(new Intent(ServiceRequestActivity.this,MainActivity.class));
                alertDialog.dismiss();
            }
        });

        TextView no=dialogView.findViewById(R.id.no);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss();
            }
        });




        alertDialog.show();

    }
}

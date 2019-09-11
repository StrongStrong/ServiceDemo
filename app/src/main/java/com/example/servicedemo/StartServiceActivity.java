package com.example.servicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class StartServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
        Log.i("Strong", "Thread ID = " + Thread.currentThread().getId());
        Log.i("Strong", "before StartService");

        //连续启动Service
        Intent intentOne = new Intent(this, StartService.class);
        startService(intentOne);
        Intent intentTwo = new Intent(this, StartService.class);
        startService(intentTwo);
        Intent intentThree = new Intent(this, StartService.class);
        startService(intentThree);

        //停止Service
        Intent intentFour = new Intent(this, StartService.class);
        stopService(intentFour);

        //再次启动Service
        Intent intentFive = new Intent(this, StartService.class);
        startService(intentFive);

        Log.i("Strong", "after StartService");
    }
}

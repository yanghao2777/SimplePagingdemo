package com.qascg.pagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    private MutableLiveData<Boolean> gotoMain = new MutableLiveData<>();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            gotoMain.setValue(!isDestroyed() && !isFinishing());
        },3000);

        gotoMain.observe(this,it -> {
            if(it){
                startActivity(new Intent(this,MainActivity.class));
                finish();
                //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
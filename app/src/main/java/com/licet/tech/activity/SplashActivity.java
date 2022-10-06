package com.licet.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.licet.tech.MainActivity;
import com.licet.tech.R;


public class SplashActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /**Fire base intialization*/
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);

                    Intent i=new Intent(SplashActivity.this, MainActivity.class);
                    finish();
                    startActivity(i);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
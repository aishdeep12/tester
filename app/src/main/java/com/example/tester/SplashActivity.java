package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class SplashActivity extends AppCompatActivity {
    Boolean ontouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
            }

        },1500);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            ontouch = true;
            startActivity(new Intent(SplashActivity.this ,LoginActivity.class));
            finish();
            return true;
        }
        return false;
    }
}

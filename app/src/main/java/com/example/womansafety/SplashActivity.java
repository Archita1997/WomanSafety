package com.example.womansafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    Runnable runnable;
    private Handler handler;


    private static int SPLASH_TIME_OUT = 5000;
    DatabaseHelper mydb;

    RelativeLayout skip;
    int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        skip = findViewById(R.id.skip);


        mydb = new DatabaseHelper(getApplicationContext());

        ImageView imageView = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        imageView.startAnimation(animation);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(homeIntent);

                finish();


            }
        }, SPLASH_TIME_OUT);

    }
}
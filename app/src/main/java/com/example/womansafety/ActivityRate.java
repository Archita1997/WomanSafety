package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ActivityRate extends AppCompatActivity {

    Toolbar toolbar_rate;

    RatingBar ratingBar;
    Button btn_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);


        ratingBar=findViewById(R.id.rating_bar);
        btn_rate=findViewById(R.id.btn_rating_bar);



        toolbar_rate=(Toolbar)findViewById(R.id.rate_toolbar);
        setSupportActionBar(toolbar_rate);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratingValue=ratingBar.getRating();
                Toast.makeText(getApplicationContext(), "Rating"+ratingValue, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

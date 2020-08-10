package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityLaw extends AppCompatActivity {

    Toolbar toolbar3;

    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        txt1=(TextView)findViewById(R.id.link1);
        txt1.setMovementMethod(LinkMovementMethod.getInstance());

        txt2=(TextView)findViewById(R.id.link2);
        txt2.setMovementMethod(LinkMovementMethod.getInstance());

        txt3=(TextView)findViewById(R.id.link3);
        txt3.setMovementMethod(LinkMovementMethod.getInstance());

        txt4=(TextView)findViewById(R.id.link4);
        txt4.setMovementMethod(LinkMovementMethod.getInstance());

        txt5=(TextView)findViewById(R.id.link5);
        txt5.setMovementMethod(LinkMovementMethod.getInstance());

        txt6=(TextView)findViewById(R.id.link6);
        txt6.setMovementMethod(LinkMovementMethod.getInstance());

        txt7=(TextView)findViewById(R.id.link7);
        txt7.setMovementMethod(LinkMovementMethod.getInstance());

        txt8=(TextView)findViewById(R.id.link8);
        txt8.setMovementMethod(LinkMovementMethod.getInstance());

        txt9=(TextView)findViewById(R.id.link9);
        txt9.setMovementMethod(LinkMovementMethod.getInstance());

        txt10=(TextView)findViewById(R.id.link10);
        txt10.setMovementMethod(LinkMovementMethod.getInstance());

        txt11=(TextView)findViewById(R.id.link11);
        txt11.setMovementMethod(LinkMovementMethod.getInstance());

        toolbar3=(Toolbar)findViewById(R.id.law_toolbar);
        setSupportActionBar(toolbar3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

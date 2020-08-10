package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    GridLayout mainGrid;
    CardView card_1;
    CardView card_3;
    CardView card_2;
    CardView card_4;


    Toast backToast;
    long back;
    DrawerLayout drawerLayout;

    DatabaseHelper mydb;

    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    Button btn2;

    ProgressBar progressBar;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Double latitude;
    Double longitude;
    String address;
    String uri;

    ImageButton emergency;
    ArrayList<String> numList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        mydb = new DatabaseHelper(getApplicationContext());
        progressBar = findViewById(R.id.p1);
        btn2 = findViewById(R.id.btn_add_number);


        mainGrid = (GridLayout) findViewById(R.id.main_grid);

        card_1 = (CardView) findViewById(R.id.card1);
        card_2 = (CardView) findViewById(R.id.card2);
        card_3 = (CardView) findViewById(R.id.card3);
        card_4 = (CardView) findViewById(R.id.card4);


        numList = new ArrayList<String>();
        emergency = findViewById(R.id.btn_emergency);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor result = mydb.getNumber();
                    if (result.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Add Number to send Emergency Alert", Toast.LENGTH_SHORT).show();
                    } else {
                        while (result.moveToNext()) {
                            numList.add(result.getString(0));
                        }
                    }
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location != null) {
                                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                                    try {
                                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                        latitude = addresses.get(0).getLatitude();
                                        longitude = addresses.get(0).getLongitude();
                                        address = addresses.get(0).getAddressLine(0);
                                        String msg = "Hi,I am in trouble,please help me by reaching to below location.Google Map Location ";
                                        String uri = "http://maps.google.com/maps?saddr=" + latitude + "," + longitude + address;
                                        ;

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                                        SmsManager sms = SmsManager.getDefault();
                                        for (int i = 0; i < numList.size(); i++) {
                                            sms.sendTextMessage(numList.get(i), null, uri, pi, null);

                                        }


                                        Toast.makeText(getApplicationContext(), "Emergency Alert Sent successfully!",
                                                Toast.LENGTH_LONG).show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        });
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityAddNumber.class));
            }
        });

        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intTips = new Intent(MainActivity.this, ActivityTips.class);
                startActivity(intTips);

            }
        });

        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intLaw = new Intent(MainActivity.this, ActivityLaw.class);
                startActivity(intLaw);

            }
        });

        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intEscape = new Intent(MainActivity.this, ActivityEscape.class);
                startActivity(intEscape);
            }
        });


        card_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intDefence = new Intent(MainActivity.this, ActivityDefence.class);
                startActivity(intDefence);

            }
        });

        btn2 = findViewById(R.id.btn_add_number);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intAddNumber = new Intent(MainActivity.this, ActivityAddNumber.class);
                startActivity(intAddNumber);

            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }


    public void setupToolbar() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }


    @Override
    public void onBackPressed() {

        if (back + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getApplicationContext(), "please back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        back = System.currentTimeMillis();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.about:


                Intent aboutIntent = new Intent(MainActivity.this, ActivityAbout.class);
                startActivity(aboutIntent);


                break;
            case R.id.help:

                AlertDialog.Builder alert1 = new AlertDialog.Builder(MainActivity.this);
                alert1.setCancelable(false);
                alert1.setTitle("Help");
                alert1.setMessage("Coming Soon");
                alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert1.create().show();
                break;

            case R.id.rate:

                final RatingBar ratingBar =new RatingBar(MainActivity.this);
                AlertDialog.Builder alert2=new AlertDialog.Builder(MainActivity.this);
                alert2.setView(ratingBar);
                alert2.setCancelable(false);
                alert2.setTitle("Rate this app");
                alert2.setMessage("Tell what you think");
                alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float ratingvalue=ratingBar.getRating();
                        sendEmail("archita.chatterjee01@gmain.com","Rating","Rating is :"+ratingvalue);
                        dialog.cancel();
                    }
                });
                alert2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                alert2.create().show();
                break;

            case R.id.share:

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Woman Safety");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Woman Safety");
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

        @SuppressLint("LongLogTag")
        protected <message> void sendEmail (String to, String sub, String message){


            Log.i("Send email", "");

            String TO = to;
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }

        public boolean checkpermission (String Permission)
        {
            int check = ContextCompat.checkSelfPermission(this, Permission);
            return (check == PackageManager.PERMISSION_GRANTED);
        }
    }



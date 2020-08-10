package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityAddNumber extends AppCompatActivity {


    Toolbar toolbar4;

    Button b1;
    private static final int REQUEST_CODE = 1;
    DatabaseHelper mydb;
    ListAdapter listAdapter;
    ListView listView;
    String n, p, i;
    ArrayList<String> list1, list2, list3, l1, l2, l3;
    ImageView call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);


        toolbar4 = (Toolbar) findViewById(R.id.add_number_toolbar);
        setSupportActionBar(toolbar4);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            b1 = findViewById(R.id.btn_add_number);
            listView = findViewById(R.id.list_view);
            listAdapter = new com.example.womansafety.ListAdapter(getApplicationContext(), R.layout.listview);
            listView.setAdapter(listAdapter);
            mydb = new DatabaseHelper(getApplicationContext());
            list1 = new ArrayList<String>();
            list2 = new ArrayList<String>();
            list3 = new ArrayList<String>();
            call = findViewById(R.id.call);



            Cursor res = mydb.getData();
            while (res.moveToNext()) {
                i = res.getString(0);
                n = res.getString(1);
                p = res.getString(2);
                list1.add(i);
                list2.add(n);
                list3.add(p);

            }
            final MyAdapter myAdapter = new MyAdapter(getApplicationContext(), list2, list3);
            listView.setAdapter(myAdapter);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Uri uri = Uri.parse("content://contacts");
                    Intent intent = new Intent(Intent.ACTION_PICK, uri);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, REQUEST_CODE);

                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ActivityAddNumber.this);
                    alert.setTitle("Call");
                    alert.setCancelable(false);
                    alert.setMessage("Are you want to call this contact\nName: " + list2.get(position) + "\nPhone:" + list3.get(position) + "\nfrom your list?");
                    alert.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Cursor res = mydb.getData();
                                list1.clear();
                                list2.clear();
                                list3.clear();

                                while (res.moveToNext()) {
                                    i = res.getString(0);
                                    n = res.getString(1);
                                    p = res.getString(2);
                                    list1.add(i);
                                    list2.add(n);
                                    list3.add(p);

                                }
                                String number = list3.get(position);

                                Intent i = new Intent(Intent.ACTION_DIAL);
                                i.setData(Uri.parse("tel:" + number));
                                startActivity(i);


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            dialog.dismiss();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.create().show();

                }
            });


            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(ActivityAddNumber.this);
                    alert.setTitle("DELETE...");
                    alert.setCancelable(false);
                    alert.setMessage("Are you want to delete this contact\nName: " + list2.get(position) + "\nPhone:" + list3.get(position) + "\nfrom your list?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String ID = list1.get(position);
                            Integer r = mydb.delete(ID);
                            if (r > 0) {
                                try {
                                    Cursor res = mydb.getData();
                                    list1.clear();
                                    list2.clear();
                                    list3.clear();

                                    while (res.moveToNext()) {
                                        i = res.getString(0);
                                        n = res.getString(1);
                                        p = res.getString(2);
                                        list1.add(i);
                                        list2.add(n);
                                        list3.add(p);

                                    }

                                    MyAdapter myAdapter1 = new MyAdapter(getApplicationContext(), list2, list3);
                                    listView.setAdapter(myAdapter1);


                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not Deleted", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.create().show();
                    return true;
                }
            });

        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode,
        Intent intent){
            super.onActivityResult(requestCode, resultCode, intent);
            if (requestCode == REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                    Cursor cursor = getContentResolver().query(uri, projection,
                            null, null, null);
                    cursor.moveToFirst();

                    int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberColumnIndex);

                    int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = cursor.getString(nameColumnIndex);

                    boolean isInserted = mydb.insertData(name, number);
                    if (isInserted == true)
                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_SHORT).show();

                    Cursor res = mydb.getData();
                    list1.clear();
                    list2.clear();
                    list3.clear();

                    while (res.moveToNext()) {
                        i = res.getString(0);
                        n = res.getString(1);
                        p = res.getString(2);

                        list1.add(i);
                        list2.add(n);
                        list3.add(p);


                    }
                    if (name.equals(n) && number.equals(p)) {
                        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), list2, list3);
                        listView.setAdapter(myAdapter);
                    }


                }
            }
        }
        ;

        class MyAdapter extends ArrayAdapter<String> {
            Context context;

            ArrayList<String> l2;
            ArrayList<String> l3;

            MyAdapter(Context c, ArrayList<String> l2, ArrayList<String> l3) {
                super(c, R.layout.listview, R.id.name, l2);
                this.context = c;

                this.l2 = l2;
                this.l3 = l3;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View row = layoutInflater.inflate(R.layout.listview, parent, false);

                TextView n1 = row.findViewById(R.id.name);
                TextView p1 = row.findViewById(R.id.phone);

                n1.setText(l2.get(position));
                p1.setText(l3.get(position));
                return row;
            }
        }


        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if (item.getItemId() == android.R.id.home) {
                finish();
            }
            return super.onOptionsItemSelected(item);
        }
    }

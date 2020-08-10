package com.example.womansafety;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView ID,NAME,PHONE;

    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row=convertView;
        LayoutHandler layoutHandler;
        if (row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.listview,parent,false);

            layoutHandler=new LayoutHandler();
            layoutHandler.ID=(TextView)row.findViewById(R.id.id);
            layoutHandler.NAME=(TextView)row.findViewById(R.id.name);
            layoutHandler.PHONE=(TextView)row.findViewById(R.id.phone);
            row.setTag(layoutHandler);

        }else {
            layoutHandler=(LayoutHandler)row.getTag();
        }
        dataProvider dataprovider=(dataProvider)this.getItem(position);

        layoutHandler.ID.setText(dataprovider.getId());
        layoutHandler.NAME.setText(dataprovider.getName());
        layoutHandler.PHONE.setText(dataprovider.getPhone());





        return row;
    }
}

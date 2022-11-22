package com.example.capstonedesign.student;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;

import java.util.ArrayList;

public class StuAtItemAdapter extends BaseAdapter {
    private ArrayList<StuAtItem> listViewItemList = new ArrayList<>();
    private Activity activity;
    public StuAtItemAdapter(){};

    public ArrayList<StuAtItem> getListViewItemList() {
        return listViewItemList;
    }

    @Override
    public int getCount() {return listViewItemList.size();}

    @Override
    public Object getItem(int pos) {return listViewItemList.get(pos);}

    @Override
    public long getItemId(int pos) {return pos;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stu_at_item,parent,false);
        }

        TextView attendance = (TextView) convertView.findViewById(R.id.attendance);
        StuAtItem listViewItem = listViewItemList.get(position);

        attendance.setText(listViewItem.getDate());
        return convertView;

    }
    public void setActivity(Activity activity){
        this.activity =activity;
    }

    public void addItem(String date){
        StuAtItem item = new StuAtItem();
        item.setDate(date);
        listViewItemList.add(item);
    }
    public void delItem(String pos){
        listViewItemList.remove(pos);
    }

}
package com.example.capstonedesign.student;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;

import java.util.ArrayList;

public class InquireChildAdapter extends BaseAdapter {
    private ArrayList<InquireChildItem> listViewItemList = new ArrayList<>();
    private Activity activity;
    public InquireChildAdapter(){};

    public ArrayList<InquireChildItem> getListViewItemList(){return listViewItemList;}
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int pos) {
        return listViewItemList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.inquire_child_item,parent,false);
        }

        TextView childName = (TextView) convertView.findViewById(R.id.inquire_child);
        InquireChildItem listViewItem = listViewItemList.get(position);

        childName.setText(listViewItem.getName());
        return convertView;
    }
    public void setActivity(Activity activity){
        this.activity =activity;
    }
    public void addItem(String name){
        InquireChildItem item = new InquireChildItem();
        item.setName(name);
        listViewItemList.add(item);
    }
    public void delItem(String pos){
        listViewItemList.remove(pos);
    }

}

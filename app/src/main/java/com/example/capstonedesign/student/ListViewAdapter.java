package com.example.capstonedesign.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();

    public ListViewAdapter(){};

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
        final int pos =position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item,parent,false);
        }

        TextView sidView = (TextView) convertView.findViewById(R.id.sid);

        ListViewItem listViewItem = listViewItemList.get(position);

        sidView.setText(listViewItem.getId());

        return convertView;
    }


    public void addItem(String sid){
        ListViewItem item = new ListViewItem();
        item.getId();
        listViewItemList.add(item);
    }

    public void delItem(String pos){
        listViewItemList.remove(pos);
    }
}

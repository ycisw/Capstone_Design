package com.example.capstonedesign.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.StudentProfile;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();
    private Activity activity;

    public ListViewAdapter(){};

    public ArrayList<ListViewItem> getListViewItemList() {
        return listViewItemList;
    }

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

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        TextView pnameView = (TextView) convertView.findViewById(R.id.pname);
        TextView pphoneView = (TextView) convertView.findViewById(R.id.pphone);

        ListViewItem listViewItem = listViewItemList.get(position);
        nameView.setText(listViewItem.getName());
        pnameView.setText(listViewItem.getPname());
        pphoneView.setText(listViewItem.getPphone());
        long sid = listViewItem.getSid();

        View finalConvertView = convertView;
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    finalConvertView.setBackgroundColor(Color.LTGRAY);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    finalConvertView.setBackgroundColor(Color.TRANSPARENT);
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_CANCEL){
                    finalConvertView.setBackgroundColor(Color.TRANSPARENT);
                }
            return false;
            }
        });
        convertView.setOnClickListener(v->{
            Intent intent = new Intent(activity, StudentProfile.class);
            intent.putExtra("sid",sid);
            activity.startActivity(intent);
        });
        return convertView;
    }

    public void setActivity(Activity activity){
        this.activity =activity;
    }

    public void addItem(Long sid,String name, String pname, String pphone){
        ListViewItem item = new ListViewItem();
        item.setSid(sid);
        item.setName(name);
        item.setPname(pname);
        item.setPphone(pphone);
        listViewItemList.add(item);
    }

    public void delItem(String pos){
        listViewItemList.remove(pos);
    }
}

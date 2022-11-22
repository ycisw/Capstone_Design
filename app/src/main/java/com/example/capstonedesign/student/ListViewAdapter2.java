package com.example.capstonedesign.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;

import java.util.ArrayList;

public class ListViewAdapter2  extends BaseAdapter {
    private ArrayList<ListViewItem2> listViewItemList = new ArrayList<>();
    private Activity activity;

    public ListViewAdapter2(){};

    public ArrayList<ListViewItem2> getListViewItemList() {
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
            convertView = inflater.inflate(R.layout.list_view_item_2,parent,false);
        }

        TextView searchView = (TextView) convertView.findViewById(R.id.search_parent_name);
        TextView snameView = (TextView) convertView.findViewById(R.id.search_student_name);

        ListViewItem2 listViewItem = listViewItemList.get(position);
        searchView.setText(listViewItem.getPname());
        snameView.setText(listViewItem.getSname());
        String pphone = listViewItem.getPphone();

        convertView.setOnClickListener(v->{
            activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pphone)));
        });
        return convertView;
    }

    public void setActivity(Activity activity){
        this.activity =activity;
    }

    public void addItem(String sname, String pname, String pphone){
        ListViewItem2 item = new ListViewItem2();
        item.setSname(sname);
        item.setPname(pname);
        item.setPphone(pphone);
        listViewItemList.add(item);
    }

    public void delItem(String pos){
        listViewItemList.remove(pos);
    }

}

package com.example.capstonedesign.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.server.domain.attendance.StudentParentForAttendance;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
//    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();
    private ArrayList<StudentParentForAttendance> listViewItemList = new ArrayList<>();

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

//        TextView sidView = (TextView) convertView.findViewById(R.id.sid);
        //뷰도 조금 바꿔놓았어요.
        TextView studentName = convertView.findViewById(R.id.student_parent_for_attendance_student_name);
        TextView parentPhone = convertView.findViewById(R.id.student_parent_for_attendance_parent_phone);

//        ListViewItem listViewItem = listViewItemList.get(position);
        StudentParentForAttendance item = listViewItemList.get(position);

//        sidView.setText(listViewItem.getId());
        studentName.setText(item.getStudent().getName());
        parentPhone.setText(item.getParent().getPhone());

        return convertView;
    }


//    public void addItem(String sid){
//        ListViewItem item = new ListViewItem();
//        item.getId();
//        listViewItemList.add(item);
//    }

    /**
     * 서버에서 받아온 데이터를 넣어봤어요.
     * @param studentParentForAttendance
     */
    public void addItem(StudentParentForAttendance studentParentForAttendance) {
        listViewItemList.add(studentParentForAttendance);
    }

    public void delItem(String pos){
        listViewItemList.remove(pos);
    }
}

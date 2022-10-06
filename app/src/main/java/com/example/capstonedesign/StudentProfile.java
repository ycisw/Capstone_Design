package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.server.domain.network.NetworkLogic;
import com.example.capstonedesign.server.domain.student.StudentParent;
import com.example.capstonedesign.server.service.StudentService;
import com.example.capstonedesign.student.ListViewAdapter;
import com.example.capstonedesign.student.ListViewItem;

import java.util.List;

public class StudentProfile extends AppCompatActivity {
    private ListViewAdapter adapter = new ListViewAdapter();
    private ListViewItem listViewItem;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        int pos = getIntent().getIntExtra("position",0);
        dialog = new Dialog(StudentProfile.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_student_profile_dialog);

        TextView sname = (TextView) findViewById(R.id.sname);
//        sname.setText(listViewItem.getName());
        Button back = (Button) findViewById(R.id.back2);
        Button student_update = (Button) findViewById(R.id.student_update);

        back.setOnClickListener(v->{
            finish();
        });

        student_update.setOnClickListener(v->{
            showProfileDialog();
        });

    }

    public void showProfileDialog(){
        dialog.show();

        Button back = dialog.findViewById(R.id.back4);

        back.setOnClickListener(v->{
            dialog.dismiss();
        });
    }
}
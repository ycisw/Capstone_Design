package com.example.capstonedesign;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.server.domain.student.StudentParent;

import java.util.HashSet;
import java.util.List;

/**
 * AttendanceCheck RecyclerView를 위한 Adapter
 * context는 클릭이벤트에서 화면 넘김을 위해 사용
 * checkSet은 체크된 요소를 빠르게 반환받기 위해 사용
 */
public class AttendanceCheckAdapter extends RecyclerView.Adapter<AttendanceCheckAdapter.ViewHolder> {
    private AppCompatActivity context; //현재 화면
    private List<AttendanceCheckModel> list; //요소들
    private HashSet<StudentParent> checkSet; //체크된 요소들

    public AttendanceCheckAdapter(AppCompatActivity context, List<AttendanceCheckModel> list) {
        this.context = context;
        this.list = list;
        checkSet = new HashSet<>();
    }

    public HashSet<StudentParent> getCheckSet() {
        return checkSet;
    }

    class ViewHolder extends RecyclerView.ViewHolder { //RecyclerView에서 보여주는 뷰들
        private TextView studentName; //학생 이름
        private CheckBox checkBox; //체크박스
        private LinearLayout rowItem; //현재 요소를 클릭했을 때의 이벤트를 처리하기 위해 사용함

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initialize(itemView); //초기화
            onClicked(); //클릭이벤트 처리
        }

        private void onClicked() {
            rowItem.setOnClickListener(v -> { //현재 요소 클릭하면 해당 학생번호를 넘기면서 화면 넘김
                context.finish();
                Intent intent = new Intent(context, personal_student.class);
                intent.putExtra("studentId", list.get(getAdapterPosition()).getStudentParent().getStudent().getId());
                context.startActivity(intent);
            });

            checkBox.setOnClickListener(v -> { //체크박스 체크하면 HashSet으로 관리 및 model에 현재 상태 저장 및 반영
                AttendanceCheckModel model = list.get(getAdapterPosition());
                if (checkBox.isChecked()) {
                    checkSet.add(model.getStudentParent());
                    model.setSelect(true);
                } else {
                    checkSet.remove(model.getStudentParent());
                    model.setSelect(false);
                }
                notifyDataSetChanged();
            });
        }

        private void initialize(@NonNull View itemView) {
            studentName = itemView.findViewById(R.id.attendance_check_student_name);
            checkBox = itemView.findViewById(R.id.attendance_check_check_box);
            rowItem = itemView.findViewById(R.id.attendance_check_recyclerview_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //뷰홀더 만들어줌
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //해당 뷰가 RecyclerView의 요소에 해당함
        View view = layoutInflater.inflate(R.layout.attendancecheck_recycler_view_item, parent, false);
        AttendanceCheckAdapter.ViewHolder viewHolder = new AttendanceCheckAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //현재 상태가 업데이트 되면 모델과 뷰의 상태를 바인딩해줌
        AttendanceCheckModel model = list.get(position);
        holder.studentName.setText(model.getStudentParent().getStudent().getName()); //학생 이름 지정
        holder.checkBox.setChecked(model.isSelect()); //체크박스 상태 동기화
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

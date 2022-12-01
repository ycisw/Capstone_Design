package com.example.capstonedesign.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;

import java.util.HashSet;
import java.util.List;

/**
 * StuAt RecyclerView를 위한 Adapter
 * context는 클릭이벤트에서 화면 넘김을 위해 사용
 * checkSet은 체크된 요소를 빠르게 반환받기 위해 사용
 */
public class StuAtItemRecycleAdapter extends RecyclerView.Adapter<StuAtItemRecycleAdapter.ViewHolder> {
    private AppCompatActivity context; //현재 화면
    private List<StuAtItem> list; //요소들
    private HashSet<StuAtItem> checkSet; //체크된 요소들

    public StuAtItemRecycleAdapter(AppCompatActivity context, List<StuAtItem> list) {
        this.context = context;
        this.list = list;
        checkSet = new HashSet<>();
    }

    public HashSet<StuAtItem> getCheckSet() {
        return checkSet;
    }


    class ViewHolder extends RecyclerView.ViewHolder { //RecyclerView에서 보여주는 뷰들
        private TextView attendanceDate;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initialize(itemView); //초기화
            onClicked(); //클릭이벤트 처리
        }

        private void onClicked() {
            checkBox.setOnClickListener(v -> { //체크박스 체크하면 HashSet으로 관리 및 model에 현재 상태 저장 및 반영
                StuAtItem model = list.get(getAdapterPosition());
                if (checkBox.isChecked()) {
                    checkSet.add(model);
                    model.setSelect(true);
                } else {
                    checkSet.remove(model);
                    model.setSelect(false);
                }
                notifyDataSetChanged();
            });
        }

        private void initialize(@NonNull View itemView) {
            attendanceDate = itemView.findViewById(R.id.attendance);
            checkBox = itemView.findViewById(R.id.attendance_delete_check_box);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //뷰홀더 만들어줌
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //해당 뷰가 RecyclerView의 요소에 해당함
        View view = layoutInflater.inflate(R.layout.stu_at_item, parent, false);
        StuAtItemRecycleAdapter.ViewHolder viewHolder = new StuAtItemRecycleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //현재 상태가 업데이트 되면 모델과 뷰의 상태를 바인딩해줌
        StuAtItem model = list.get(position);
        holder.attendanceDate.setText(model.getDate()); //학생 이름 지정
        holder.checkBox.setChecked(model.isSelect()); //체크박스 상태 동기화
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

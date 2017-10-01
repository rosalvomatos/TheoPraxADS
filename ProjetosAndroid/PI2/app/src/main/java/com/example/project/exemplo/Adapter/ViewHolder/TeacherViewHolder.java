package com.example.project.exemplo.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;

public class TeacherViewHolder extends RecyclerView.ViewHolder {
    public Button Discipline;
    public TextView Name;
    public TextView CodeTeacher;
    public TextView Id;
    public TextView Hiring;


    public TeacherViewHolder(View itemView) {
        super(itemView);
        Discipline = (Button) itemView.findViewById(R.id.btnShowDiscipline);
        Name = (TextView) itemView.findViewById(R.id.textViewNameTeacher);
        Hiring = (TextView) itemView.findViewById(R.id.textViewHiring);
        CodeTeacher = (TextView) itemView.findViewById(R.id.textViewCodeTeacher);
    }
}

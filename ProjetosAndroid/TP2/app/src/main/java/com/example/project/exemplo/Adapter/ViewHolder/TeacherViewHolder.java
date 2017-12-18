package com.example.project.exemplo.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;

public class TeacherViewHolder extends RecyclerView.ViewHolder {

    public Button Discipline;
    public Button Lattes;
    public TextView Name;
    public TextView Hiring;


    public TeacherViewHolder(View itemView) {
        super(itemView);
        Discipline = (Button) itemView.findViewById(R.id.btnShowDiscipline);
        Lattes = (Button) itemView.findViewById(R.id.btnShowLattes);
        Name = (TextView) itemView.findViewById(R.id.textViewNameTeacher);
        Hiring = (TextView) itemView.findViewById(R.id.textViewHiring);
    }
}

package com.example.project.exemplo.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;

public class DisciplineViewHolder extends RecyclerView.ViewHolder {

    public Button Teacher;
    public Button Menu;
    public TextView Name;
    public TextView CodeDiscipline;
    public TextView Id;
    public TextView Duration;

    public DisciplineViewHolder(View itemView) {
        super(itemView);
        Teacher = (Button) itemView.findViewById(R.id.btnShowTeacher);
        Menu = (Button) itemView.findViewById(R.id.btnShowMenu);
        Name = (TextView) itemView.findViewById(R.id.textViewNameDiscipline);
        Duration = (TextView) itemView.findViewById(R.id.textViewDurationDiscipline);
        CodeDiscipline = (TextView) itemView.findViewById(R.id.textViewCodeDiscipline);
    }
}

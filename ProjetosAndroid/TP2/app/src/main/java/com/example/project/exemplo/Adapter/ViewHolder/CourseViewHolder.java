package com.example.project.exemplo.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    public TextView Name;
    public TextView id;
    public TextView CodeCourse;
    public TextView Shift;
    public TextView Modality;
    public TextView MECEvaluation;
    public TextView Duration;
    public TextView Coordinator;
    public TextView MonthlyPayment;
    public Button AuthorizationAct;
    public Button CoursePedagogicalPlan;
    public Button BibliographicCollection;
    public Button Teacher;
    public Button Curriculum;
    public Button Discipline;

    public CourseViewHolder(View itemView) {
        super(itemView);
        Name = (TextView) itemView.findViewById(R.id.textViewName);
        CodeCourse = (TextView) itemView.findViewById(R.id.textViewCodeCourse);
        Shift = (TextView) itemView.findViewById(R.id.textViewShift);
        Modality = (TextView) itemView.findViewById(R.id.textViewModality);
        MECEvaluation = (TextView) itemView.findViewById(R.id.textViewMECEvaluation);
        Duration = (TextView) itemView.findViewById(R.id.textViewDuration);
        Coordinator = (TextView) itemView.findViewById(R.id.textViewCoordinator);
        MonthlyPayment = (TextView) itemView.findViewById(R.id.textViewMonthlyPayment);
        AuthorizationAct = (Button) itemView.findViewById(R.id.btnShowAuthorizationAct);
        CoursePedagogicalPlan = (Button) itemView.findViewById(R.id.btnShowCoursePedagogicalPlan);
        BibliographicCollection = (Button) itemView.findViewById(R.id.btnShowBiBliographiPlan);
        Teacher = (Button) itemView.findViewById(R.id.btnShowTeacher);
        Curriculum = (Button) itemView.findViewById(R.id.btnShowCurriculum);
        Discipline = (Button) itemView.findViewById(R.id.btnShowDiscipline);
    }
}

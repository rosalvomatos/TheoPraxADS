package com.example.project.exemplo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.exemplo.Adapter.Interface.ICourseListener;
import com.example.project.exemplo.Adapter.ViewHolder.CourseViewHolder;
import com.example.project.exemplo.Mapper.Json.CourseJson;
import com.example.project.exemplo.adaptadores.CursosRVAdapter;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder>{
    List<CourseJson> courseJsonList;
    private int rowLayout;
    private Context context;
    public ICourseListener courseListener;

    public CourseAdapter(List<CourseJson> courseJsonList, int rowLayout, Context context, ICourseListener courseListener) {
        this.courseJsonList = courseJsonList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.courseListener = courseListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        String name = courseJsonList.get(position).getNome();
        String codeCourse = courseJsonList.get(position).getCodigo();
        String shift = courseJsonList.get(position).getTurno();
        String modality = courseJsonList.get(position).getModalidade();
        int mecEvaluation = courseJsonList.get(position).getNotaMEC();
        String duration = courseJsonList.get(position).getCH();
        String coordinator  = courseJsonList.get(position).getCoordenador();
        String monthlyPayment = courseJsonList.get(position).getMensalidade();

        holder.Name.setText(name == null ? "???" : name);
        holder.CodeCourse.setText(codeCourse == null ? "???" : codeCourse);
        holder.Shift.setText(shift == null ? "???" : shift);
        holder.Modality.setText(modality == null ? "???" : modality);
        holder.MECEvaluation.setText(Integer.toString(mecEvaluation));
        holder.Coordinator.setText(coordinator == null ? "???" : coordinator);
        holder.Duration.setText(duration == null ? "???" : duration);
        holder.MonthlyPayment.setText(monthlyPayment == null ? "???" : monthlyPayment);

        final  CourseJson courseActual = courseJsonList.get(position);

        holder.AuthorizationAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showAuthorizationAct(courseActual);
                }
            }
        });

        holder.CoursePedagogicalPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showCoursePedagogicalPlan(courseActual);
                }
            }
        });

        holder.BibliographicCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showBibliographicCollection(courseActual);
                }
            }
        });

        holder.Curriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showCurriculum(courseActual);
                }
            }
        });

        holder.Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showTeacher(courseActual);
                }
            }
        });

        holder.Discipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseListener != null){
                    courseListener.showDiscipline(courseActual);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseJsonList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

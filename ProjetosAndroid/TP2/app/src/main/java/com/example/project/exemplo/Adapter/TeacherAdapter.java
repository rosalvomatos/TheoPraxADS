package com.example.project.exemplo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.exemplo.Adapter.Interface.ITeacherListener;
import com.example.project.exemplo.Adapter.ViewHolder.TeacherViewHolder;
import com.example.project.exemplo.Mapper.Json.TeacherJson;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherViewHolder> {

    List<TeacherJson> teacherJsonList;
    private int rowLayout;
    private Context context;
    public ITeacherListener teacherListener;

    public TeacherAdapter(List<TeacherJson> teacherJsonList, int rowLayout, Context context, ITeacherListener teacherListener) {
        this.teacherJsonList = teacherJsonList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.teacherListener = teacherListener;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TeacherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        String name = teacherJsonList.get(position).getNome();
        String hiring = teacherJsonList.get(position).getModContrato();

        holder.Name.setText(name == null ? "???" : name);
        holder.Hiring.setText(hiring == null ? "???" : hiring);

        final TeacherJson teacherJson = teacherJsonList.get(position);

        holder.Discipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teacherListener != null) {
                    teacherListener.showDiscipline(teacherJson);
                }
            }
        });

        holder.Lattes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teacherListener != null) {
                    teacherListener.showLattes(teacherJson);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherJsonList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

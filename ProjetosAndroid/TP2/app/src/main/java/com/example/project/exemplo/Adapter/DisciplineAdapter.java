package com.example.project.exemplo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.exemplo.Adapter.Interface.IDisciplineListener;
import com.example.project.exemplo.Adapter.ViewHolder.DisciplineViewHolder;
import com.example.project.exemplo.Mapper.Json.DisciplineJson;

import java.util.List;

public class DisciplineAdapter extends RecyclerView.Adapter<DisciplineViewHolder> {

    List<DisciplineJson> disciplineJsonList;
    private int rowLayout;
    private Context context;
    public IDisciplineListener disciplineListener;

    public DisciplineAdapter(List<DisciplineJson> disciplineJsonList, int rowLayout, Context context, IDisciplineListener disciplineListener) {
        this.disciplineJsonList = disciplineJsonList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.disciplineListener = disciplineListener;
    }

    @Override
    public DisciplineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DisciplineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DisciplineViewHolder holder, int position) {
        String name = disciplineJsonList.get(position).getNome();
        int duration = disciplineJsonList.get(position).getCH();

        holder.Name.setText(name == null ? "???" : name);
        holder.Duration.setText(Integer.toString(duration));

        final DisciplineJson disciplineJson = disciplineJsonList.get(position);

        holder.Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disciplineListener != null) {
                    disciplineListener.showTeacher(disciplineJson);
                }
            }
        });

        holder.Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disciplineListener != null) {
                    disciplineListener.showMenu(disciplineJson);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplineJsonList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

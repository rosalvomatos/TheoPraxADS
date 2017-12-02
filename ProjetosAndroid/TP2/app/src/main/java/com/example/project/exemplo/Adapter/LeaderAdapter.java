package com.example.project.exemplo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.exemplo.Adapter.Interface.ITeacherListener;
import com.example.project.exemplo.Adapter.ViewHolder.LeaderViewHolder;
import com.example.project.exemplo.Adapter.ViewHolder.TeacherViewHolder;
import com.example.project.exemplo.Mapper.Json.LeaderJson;
import com.example.project.exemplo.Mapper.Json.TeacherJson;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderViewHolder> {

    List<LeaderJson> leaderJsonList;
    private int rowLayout;
    private Context context;

    public LeaderAdapter(List<LeaderJson> leaderJsonList, int rowLayout, Context context) {
        this.leaderJsonList = leaderJsonList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public LeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LeaderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LeaderViewHolder holder, int position) {
        String Title = leaderJsonList.get(position).getChave();
        String Value = leaderJsonList.get(position).getValor();

        holder.Title.setText(Title == null ? "???" : Title);
        holder.Leaders.setText(Value == null ? "???" : Value);

        final LeaderJson teacherJson = leaderJsonList.get(position);

    }

    @Override
    public int getItemCount() {
        return leaderJsonList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

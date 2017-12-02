package com.example.project.exemplo.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;

public class LeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView Title;
    public TextView Leaders;


    public LeaderViewHolder(View itemView) {
        super(itemView);
        Title = (TextView) itemView.findViewById(R.id.textViewTitleLeader);
        Leaders = (TextView) itemView.findViewById(R.id.textViewLeaders );
    }
}

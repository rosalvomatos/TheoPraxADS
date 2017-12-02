package com.example.project.exemplo.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.project.exemplo.Activity.Task.LeaderTask;
import com.example.project.exemplo.Activity.Task.TeacherTask;
import com.example.project.exemplo.Adapter.LeaderAdapter;
import com.example.project.exemplo.Adapter.LeaderAdapter;
import com.example.project.exemplo.Mapper.Json.LeaderJson;
import com.example.project.exemplo.Mapper.Json.LeaderJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.Util.GenericDialogFragment;
import com.example.project.exemplo.Util.ProgressDialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LeaderActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    LeaderAdapter leaderAdapter;
    private LeaderTask leaderTask;
    private FragmentManager fragmentManager;
    static final String leaderList = "leaderList";
    static final String leaderListOrigin = "leaderListOrigin";
    List<LeaderJson> leaderJsonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leader_list);
        
        leaderAdapter = new LeaderAdapter(leaderJsonList,R.layout.layout_leader,this);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.leaders));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        fragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_leader_list);

        if (savedInstanceState != null) {
            leaderJsonList = (List<LeaderJson>) savedInstanceState.getSerializable(leaderList);
            if (leaderJsonList != null) {
                leaderAdapter = new LeaderAdapter(leaderJsonList, R.layout.layout_leader, this);
                recyclerView.setAdapter(leaderAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        } else {
            if (leaderJsonList == null) populateLeaderList();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(leaderList, (Serializable) leaderJsonList);
    }

    private void populateLeaderList() {
        leaderJsonList = new ArrayList<>();
        ProgressDialogUtil.instantiateContext(this);
        ProgressDialogUtil.setDialogMessage(R.string.msg_dialog_leader);
        try {
            if (leaderTask == null || leaderTask.getStatus() != AsyncTask.Status.RUNNING) {
                leaderTask = new LeaderTask(this, fragmentManager, leaderAdapter, leaderJsonList, recyclerView);
                leaderTask.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(LeaderActivity.this,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(fragmentManager);
        }
    }
}

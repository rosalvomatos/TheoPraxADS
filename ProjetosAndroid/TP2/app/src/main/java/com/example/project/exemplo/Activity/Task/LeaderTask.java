package com.example.project.exemplo.Activity.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project.exemplo.Adapter.LeaderAdapter;
import com.example.project.exemplo.Mapper.Json.LeaderJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.Util.CGuideWS;
import com.example.project.exemplo.Util.GenericDialogFragment;
import com.example.project.exemplo.Util.ProgressDialogUtil;

import java.util.List;

public class LeaderTask extends AsyncTask<Void, Void, List<LeaderJson>> {

    Context context;
    FragmentManager mFragmentManager;
    LeaderAdapter leaderAdapter;
    List<LeaderJson> leaderJsonList;
    RecyclerView recyclerView;

    public LeaderTask(Context context, FragmentManager mFragmentManager, LeaderAdapter leaderAdapter, List<LeaderJson> leaderJsonList, RecyclerView recyclerView) {
        this.context = context;
        this.mFragmentManager = mFragmentManager;
        this.leaderAdapter = leaderAdapter;
        this.leaderJsonList = leaderJsonList;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialogUtil.showProgressDialogUtil(true, context);
    }

    @Override
    protected List<LeaderJson> doInBackground(Void... params) {
        List<LeaderJson> leaderJsonList = null;
        try {
            leaderJsonList = CGuideWS.getLeader();
        } catch (Exception e) {
            ProgressDialogUtil.showProgressDialogUtil(false, context);
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(context,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android

                    });
            dialog.openDialog(mFragmentManager);
        }
        return leaderJsonList;
    }

    @Override
    protected void onPostExecute(List<LeaderJson> leaderJsonListLocal) {
        super.onPostExecute(leaderJsonListLocal);
        ProgressDialogUtil.showProgressDialogUtil(false, context);
        if (leaderJsonListLocal == null) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(context,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog_error,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(mFragmentManager);
        } else {
            leaderJsonList.addAll(leaderJsonListLocal);
            leaderAdapter = new LeaderAdapter(leaderJsonList, R.layout.layout_leader, context);
            recyclerView.setAdapter(leaderAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}

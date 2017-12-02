package com.example.project.exemplo.Activity.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project.exemplo.Adapter.DisciplineAdapter;
import com.example.project.exemplo.Adapter.Interface.IDisciplineListener;
import com.example.project.exemplo.Mapper.Json.DisciplineJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.Util.CGuideWS;
import com.example.project.exemplo.Util.GenericDialogFragment;
import com.example.project.exemplo.Util.ProgressDialogUtil;

import java.util.List;

public class DisciplineTask extends AsyncTask<Void, Void, List<DisciplineJson>> {

    Context context;
    int typeSearch;
    String refferId;
    FragmentManager mFragmentManager;
    DisciplineAdapter disciplineAdapter;
    List<DisciplineJson> disciplineJsonList;
    IDisciplineListener iDisciplineListener;
    RecyclerView recyclerView;

    public DisciplineTask(Context context, int typeSearch, String refferId, FragmentManager mFragmentManager, DisciplineAdapter disciplineAdapter, List<DisciplineJson> disciplineJsonList, IDisciplineListener iDisciplineListener, RecyclerView recyclerView) {
        this.context = context;
        this.typeSearch = typeSearch;
        this.refferId = refferId;
        this.mFragmentManager = mFragmentManager;
        this.disciplineAdapter = disciplineAdapter;
        this.disciplineJsonList = disciplineJsonList;
        this.iDisciplineListener = iDisciplineListener;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialogUtil.showProgressDialogUtil(true, context);
    }

    @Override
    protected List<DisciplineJson> doInBackground(Void... params) {
        List<DisciplineJson> disciplineJsonList = null;
        try {
            disciplineJsonList = CGuideWS.getDiscipline(typeSearch, refferId);
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
        return disciplineJsonList;
    }

    @Override
    protected void onPostExecute(List<DisciplineJson> disciplineJsonListLocal) {
        super.onPostExecute(disciplineJsonListLocal);
        ProgressDialogUtil.showProgressDialogUtil(false, context);
        if (disciplineJsonListLocal == null) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(context,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog_error,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(mFragmentManager);
        } else {
            disciplineJsonList.addAll(disciplineJsonListLocal);
            disciplineAdapter = new DisciplineAdapter(disciplineJsonList, R.layout.layout_discipline, context, iDisciplineListener);
            recyclerView.setAdapter(disciplineAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}

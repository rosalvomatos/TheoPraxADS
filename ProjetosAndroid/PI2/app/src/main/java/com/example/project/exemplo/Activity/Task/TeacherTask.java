package com.example.project.exemplo.Activity.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project.exemplo.Adapter.TeacherAdapter;
import com.example.project.exemplo.Adapter.Interface.ITeacherListener;
import com.example.project.exemplo.Mapper.Json.TeacherJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.GenericDialogFragment;
import com.example.project.exemplo.util.ProgressDialogUtil;

import java.util.List;

public class TeacherTask  extends AsyncTask<Void, Void, List<TeacherJson>> {

    Context context;
    int typeSearch;
    int refferId;
    FragmentManager mFragmentManager;
    TeacherAdapter teacherAdapter;
    List<TeacherJson> teacherJsonList;
    ITeacherListener iTeacherListener;
    RecyclerView recyclerView;

    public TeacherTask(Context context, int typeSearch, int refferId, FragmentManager mFragmentManager, TeacherAdapter teacherAdapter, List<TeacherJson> teacherJsonList, ITeacherListener iTeacherListener, RecyclerView recyclerView) {
        this.context = context;
        this.typeSearch = typeSearch;
        this.refferId = refferId;
        this.mFragmentManager = mFragmentManager;
        this.teacherAdapter = teacherAdapter;
        this.teacherJsonList = teacherJsonList;
        this.iTeacherListener = iTeacherListener;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialogUtil.showProgressDialogUtil(true, context);
    }

    @Override
    protected List<TeacherJson> doInBackground(Void... params) {
        List<TeacherJson> teacherJsonList = null;
        try {
            teacherJsonList = CGuideWS.getTeacher(typeSearch, refferId);
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
        return teacherJsonList;
    }

    @Override
    protected void onPostExecute(List<TeacherJson> teacherJsonListLocal) {
        super.onPostExecute(teacherJsonListLocal);
        ProgressDialogUtil.showProgressDialogUtil(false, context);
        if (teacherJsonListLocal == null) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(context,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog_teacher,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(mFragmentManager);
        } else {
            teacherJsonList.addAll(teacherJsonListLocal);
            teacherAdapter = new TeacherAdapter(teacherJsonList, R.layout.layout_teacher, context, iTeacherListener);
            recyclerView.setAdapter(teacherAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}

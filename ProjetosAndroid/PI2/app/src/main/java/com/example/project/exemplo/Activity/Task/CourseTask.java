package com.example.project.exemplo.Activity.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project.exemplo.Adapter.CourseAdapter;
import com.example.project.exemplo.Adapter.Interface.ICourseListener;
import com.example.project.exemplo.Mapper.Json.CourseJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.GenericDialogFragment;
import com.example.project.exemplo.util.ProgressDialogUtil;

import java.util.List;

public class CourseTask extends AsyncTask<Void, Void, List<CourseJson>> {

    Context context;
    int typeCourse;
    FragmentManager mFragmentManager;
    CourseAdapter courseAdapter;
    List<CourseJson> courseJsonList;
    ICourseListener iCourseListener;
    RecyclerView recyclerView;

    public CourseTask(Context context, int typeCourse, FragmentManager mFragmentManager, CourseAdapter courseAdapter, List<CourseJson> courseJsonList, ICourseListener iCourseListener, RecyclerView recyclerView) {
        this.context = context;
        this.typeCourse = typeCourse;
        this.mFragmentManager = mFragmentManager;
        this.courseAdapter = courseAdapter;
        this.courseJsonList = courseJsonList;
        this.iCourseListener = iCourseListener;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialogUtil.showProgressDialogUtil(true, context);
    }

    @Override
    protected List<CourseJson> doInBackground(Void... params) {
        List<CourseJson> courseJsonList = null;
        try {
            courseJsonList = CGuideWS.getCourse(typeCourse);
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
        return courseJsonList;
    }

    @Override
    protected void onPostExecute(List<CourseJson> courseJsonListLocal) {
        super.onPostExecute(courseJsonListLocal);
        ProgressDialogUtil.showProgressDialogUtil(false, context);
        if (courseJsonListLocal == null) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(context,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog_course,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(mFragmentManager);
        } else {
            courseJsonList.addAll(courseJsonListLocal);
            courseAdapter = new CourseAdapter(courseJsonList, R.layout.layout_course, context, iCourseListener);
            recyclerView.setAdapter(courseAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}

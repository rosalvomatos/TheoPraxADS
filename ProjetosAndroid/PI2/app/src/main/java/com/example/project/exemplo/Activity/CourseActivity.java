package com.example.project.exemplo.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.project.exemplo.Activity.Task.CourseTask;
import com.example.project.exemplo.Adapter.CourseAdapter;
import com.example.project.exemplo.Adapter.Interface.ICourseListener;
import com.example.project.exemplo.Mapper.Json.CourseJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.Enum.DisciplineTypeSearch;
import com.example.project.exemplo.util.Enum.TeacherTypeSearch;
import com.example.project.exemplo.util.GenericDialogFragment;
import com.example.project.exemplo.util.ProgressDialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    private CourseTask courseTask;
    private FragmentManager fragmentManager;
    static final String courseList = "courseList";
    static final String courseListOrigin = "courseListOrigin";
    List<CourseJson> courseJsonList;
    List<CourseJson> lastSearch;
    List<CourseJson> coursesFound;
    int typeCourse = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_course_list);
        Intent intent = getIntent();
        typeCourse = intent.getExtras().getInt("typeCourse");
        String titlePage = "Cursos de";
        titlePage += typeCourse == 1 ? " Graduação" : " Pós Graduação";

        courseAdapter = new CourseAdapter(courseJsonList, R.layout.layout_course, this, iCourseListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titlePage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_course_list);

        if (savedInstanceState != null) {
            courseJsonList = (List<CourseJson>) savedInstanceState.getSerializable(courseList);
            lastSearch = (List<CourseJson>) savedInstanceState.getSerializable(courseListOrigin);

            if (courseJsonList != null) {
                courseAdapter = new CourseAdapter(courseJsonList, R.layout.layout_course, this, iCourseListener);
                recyclerView.setAdapter(courseAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        } else {
            if (courseJsonList == null) populateCourseList();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(courseList, (Serializable) courseJsonList);
        savedInstanceState.putSerializable(courseListOrigin, (Serializable) lastSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.procurar));
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update)
            populateCourseList();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        lastSearch = courseJsonList;
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        clearSearch();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    private ICourseListener iCourseListener = new ICourseListener() {

        @Override
        public void showAuthorizationAct(CourseJson courseJson) {
            startActivity(CGuideWS.openFile("ATO_" + courseJson.getCodigo() + ".pdf"));
        }

        @Override
        public void showTeacher(CourseJson courseJson) {
            Intent intent = new Intent(CourseActivity.this, TeacherActivity.class);
            int typeSearch = TeacherTypeSearch.valueOf("ByCourse").ordinal() + 1;
            intent.putExtra("refferId", courseJson.getId());
            intent.putExtra("typeSearch", typeSearch);
            startActivity(intent);
        }

        @Override
        public void showCoursePedagogicalPlan(CourseJson courseJson) {
            startActivity(CGuideWS.openFile("PPC_" + courseJson.getCodigo() + ".pdf"));
        }

        @Override
        public void showDiscipline(CourseJson courseJson) {
            Intent intent = new Intent(CourseActivity.this, DisciplineActivity.class);
            int typeSearch = DisciplineTypeSearch.valueOf("ByCourse").ordinal() + 1;
            intent.putExtra("refferId", courseJson.getId());
            intent.putExtra("typeSearch", typeSearch);
            startActivity(intent);
        }

        @Override
        public void showBibliographicCollection(CourseJson courseJson) {

        }

        @Override
        public void showCurriculum(CourseJson courseJson) {
            startActivity(CGuideWS.openFile("MATRIZ_" + courseJson.getCodigo() + ".pdf"));
        }
    };

    private void populateCourseList() {
        courseJsonList = new ArrayList<CourseJson>();
        ProgressDialogUtil.instantiateContext(this);
        ProgressDialogUtil.setDialogMessage(R.string.msg_dialog1);
        try {
            if (courseTask == null || courseTask.getStatus() != AsyncTask.Status.RUNNING) {
                courseTask = new CourseTask(this, typeCourse, fragmentManager, courseAdapter, courseJsonList, iCourseListener, recyclerView);
                courseTask.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.novoDialog(CourseActivity.this,
                    0,
                    R.string.titulo_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.abrir(fragmentManager);
        }
    }

    public void clearSearch() {
        courseJsonList = new ArrayList<>();
        if (lastSearch != null) courseJsonList.addAll(lastSearch);
        if (courseJsonList != null) {
            courseAdapter = new CourseAdapter(courseJsonList, R.layout.layout_course, this, iCourseListener);
            recyclerView.setAdapter(courseAdapter);
            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            fragmentManager = getSupportFragmentManager();
        }
    }

    public void search(String s) {
        if (s == null || s.trim().equals("")) {
            clearSearch();
            return;
        }
        coursesFound = new ArrayList<>();
        coursesFound.addAll(courseJsonList);
        for (int i = coursesFound.size() - 1; i >= 0; i--) {
            CourseJson cursoTemp = coursesFound.get(i);
            if (!cursoTemp.getNome().toUpperCase().contains(s.toUpperCase())) {
                coursesFound.remove(cursoTemp);
            }
        }
        courseAdapter = new CourseAdapter(coursesFound, R.layout.layout_course, this, iCourseListener);
        recyclerView.setAdapter(courseAdapter);
    }
}

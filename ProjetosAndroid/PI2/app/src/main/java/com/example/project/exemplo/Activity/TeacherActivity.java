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

import com.example.project.exemplo.Activity.Task.TeacherTask;
import com.example.project.exemplo.Adapter.TeacherAdapter;
import com.example.project.exemplo.Adapter.Interface.ITeacherListener;
import com.example.project.exemplo.Mapper.Json.TeacherJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.Util.Enum.DisciplineTypeSearch;
import com.example.project.exemplo.Util.GenericDialogFragment;
import com.example.project.exemplo.Util.ProgressDialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    TeacherAdapter teacherAdapter;
    private TeacherTask teacherTask;
    private FragmentManager fragmentManager;
    static final String teacherList = "teacherList";
    static final String teacherListOrigin = "teacherListOrigin";
    List<TeacherJson> teacherJsonList;
    List<TeacherJson> lastSearch;
    List<TeacherJson> teachersFound;
    int typeSearch;
    int refferId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_teacher_list);
        Intent intent = getIntent();
        typeSearch = intent.getExtras().getInt("typeSearch");
        refferId = intent.getExtras().getInt("refferId");

        String titlePage = getResources().getString(R.string.teachers);

        teacherAdapter = new TeacherAdapter(teacherJsonList, R.layout.layout_teacher, this, iTeacherListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titlePage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_teacher_list);

        if (savedInstanceState != null) {
            teacherJsonList = (List<TeacherJson>) savedInstanceState.getSerializable(teacherList);
            lastSearch = (List<TeacherJson>) savedInstanceState.getSerializable(teacherListOrigin);
            if (teacherJsonList != null) {
                teacherAdapter = new TeacherAdapter(teacherJsonList, R.layout.layout_teacher, this, iTeacherListener);
                recyclerView.setAdapter(teacherAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        } else {
            if (teacherJsonList == null) populateTeacherList();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(teacherList, (Serializable) teacherJsonList);
        savedInstanceState.putSerializable(teacherListOrigin, (Serializable) lastSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.search_teacher));
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update)
            populateTeacherList();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        lastSearch = teacherJsonList;
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

    private ITeacherListener iTeacherListener = new ITeacherListener() {

        @Override
        public void showDiscipline(TeacherJson teacherJson) {
            Intent intent = new Intent(TeacherActivity.this, DisciplineActivity.class);
            int typeSearch = DisciplineTypeSearch.valueOf("ByTeacher").ordinal() + 1;
            intent.putExtra("refferId", teacherJson.getId());
            intent.putExtra("typeSearch", typeSearch);
            startActivity(intent);
        }
    };

    private void populateTeacherList() {
        teacherJsonList = new ArrayList<>();
        ProgressDialogUtil.instantiateContext(this);
        ProgressDialogUtil.setDialogMessage(R.string.msg_dialog_teacher);
        try {
            if (teacherTask == null || teacherTask.getStatus() != AsyncTask.Status.RUNNING) {
                teacherTask = new TeacherTask(this, typeSearch, refferId, fragmentManager, teacherAdapter, teacherJsonList, iTeacherListener, recyclerView);
                teacherTask.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.newDialog(TeacherActivity.this,
                    0,
                    R.string.title_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.openDialog(fragmentManager);
        }
    }

    public void clearSearch() {
        teacherJsonList = new ArrayList<>();
        if (lastSearch != null) teacherJsonList.addAll(lastSearch);
        if (teacherJsonList != null) {
            teacherAdapter = new TeacherAdapter(teacherJsonList, R.layout.layout_teacher, this, iTeacherListener);
            recyclerView.setAdapter(teacherAdapter);
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
        teachersFound = new ArrayList<>();
        teachersFound.addAll(teacherJsonList);
        for (int i = teachersFound.size() - 1; i >= 0; i--) {
            TeacherJson teacherTemp = teachersFound.get(i);
            if (!teacherTemp.getNome().toUpperCase().contains(s.toUpperCase())) {
                teachersFound.remove(teacherTemp);
            }
        }
        teacherAdapter = new TeacherAdapter(teachersFound, R.layout.layout_teacher, this, iTeacherListener);
        recyclerView.setAdapter(teacherAdapter);
    }
}